package com.project.meetinglive.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.meetinglive.common.util.BaseUtil;
import com.project.meetinglive.common.util.DateUtil;
import com.project.meetinglive.common.util.FileUploadHelp;
import com.project.meetinglive.core.Live.LiveClientHelp;
import com.project.meetinglive.core.Live.TecentCloudUtils;
import com.project.meetinglive.core.config.ApplicationConfig;
import com.project.meetinglive.core.data.pageBean.SinglePageBean;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.exception.ServiceException;
import com.project.meetinglive.core.wechat.WechatUserQrCodeHelp;
import com.project.meetinglive.dao.RoomDao;
import com.project.meetinglive.modal.LiveRoomModel;
import com.project.meetinglive.vo.LiveListVO;

/**
 * 直播业务层
 * @author hejinguo
 * @version $Id: ApiAdvertService.java, v 0.1 2020年7月25日 下午12:04:07
 */
@Service
public class LiveService {
    private static final Logger logger = LoggerFactory.getLogger(LiveService.class);
    @Autowired
    private RoomDao roomDao;

    /**
     * 获取会议列表
     * @return
     * @throws Exception
     */
    public SinglePageBean<LiveListVO> getLiveList(JsonMessage jsonMessage) throws Exception {
        //step1:返回对象
        SinglePageBean<LiveListVO> response = new SinglePageBean<LiveListVO>();
        //step2:获取请求参数
        Integer currentPage = jsonMessage.getInt("currentPage");
        Integer pageSize = jsonMessage.getInt("pageSize");
        response.setCurrentPage(currentPage);
        response.setPageSize(pageSize);
        //step3:设置请求参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        int totalCount = this.roomDao.getLiveListCount(paramMap);
        //step4:设置分页参数信息
        response.setTotalItems(totalCount);
        response.countAllPage();
        //step5:查询广告信息
        if (totalCount > 0) {
            paramMap.put("pageSize", response.getPageSize());
            paramMap.put("startIndex", response.gainStartIndex());
            List<LiveListVO> listBanner = this.roomDao.getLiveList(paramMap);
            response.setList(listBanner);
        }
        return response;
    }

    /**
     * 获取直播详情
     * @param jsonMessage
     * @return
     * @throws Exception
     */
    public LiveRoomModel getLiveDetail(JsonMessage jsonMessage) throws Exception {
        Integer id = jsonMessage.getInteger("id");
        if (id == null || id == 0) {
            throw new ServiceException("请求参数不能为空!");
        }
        return this.roomDao.getLiveRoomById(id);
    }

    /**
     * 添加会议信息
     * @param jsonMessage
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addLive(JsonMessage jsonMessage) throws Exception {
        //step1：获取请求参数
        String roomTitle = jsonMessage.getString("roomTitle");
        String roomPicPath = jsonMessage.getString("roomPicPath");
        String roomSchedulePath = jsonMessage.getString("roomSchedulePath");
        String roomDescPath = jsonMessage.getString("roomDescPath");
        Date startDate = DateUtil.StringToDate(jsonMessage.getString("liveStartDate"));
        LiveRoomModel.validateAddLiveParam(roomTitle, roomPicPath, roomSchedulePath, roomDescPath,
            startDate);
        //step2:添加会议信息
        LiveRoomModel liveRoomModel = new LiveRoomModel();
        liveRoomModel.setRoomTitle(roomTitle);
        liveRoomModel.setRoomPicPath(roomPicPath);
        liveRoomModel.setRoomSchedulePath(roomSchedulePath);
        liveRoomModel.setRoomDescPath(roomDescPath);
        liveRoomModel.setLiveStartDate(startDate);
        this.roomDao.addLive(liveRoomModel);
        int id = liveRoomModel.getId();
        //step3:生成腾讯直播链接
        //step3.1:设置过期时间
        Long now = System.currentTimeMillis() + 60L * 60L * 24L * 30L * 1000L;
        Long txTime = now / 1000;// 推流码过期时间秒数
        //推流过期时间
        Date pushExpireDate = DateUtil.getSomeSecondDate(txTime.intValue());
        //step3.2:推流服务器地址
        String pushServerUrl = "rtmp://" + ApplicationConfig.tecentCloud_live_pushServerurl
                               + File.separator + ApplicationConfig.tecentCloud_live_appName
                               + File.separator;
        //step3.3:房间ID
        String roomCode = ApplicationConfig.tecentCloud_live_StreamNamePrefix + "_" + id;
        //step3.4:推流秘钥
        String pushSecretKey = TecentCloudUtils.getPushSecretKey(roomCode, txTime);
        //step3.5:RTMP拉流地址
        String pullRtmpUrl = TecentCloudUtils.getPullRtmpUrl(roomCode);
        //step3.6:HLS拉流地址
        String pullHlsUrl = TecentCloudUtils.getPullHlsUrl(roomCode);
        //step3.7:Flv拉流地址
        String pullFlvUrl = TecentCloudUtils.getPullFlvUrl(roomCode);
        //step3.8:UDP拉流地址
        String pullUdpUrl = TecentCloudUtils.getPullUdpUrl(roomCode);
        

        //step4:生成直播链接二维码
        String imgName = UUID.randomUUID().toString() + ".png";
        String imgNamePath = BaseUtil.getTempFoderPath() + File.separator + imgName;
        File localFile = new File(imgNamePath);
        String userIdentify = "id=" + id;
        byte[] wechatQrCodeByte = WechatUserQrCodeHelp.getWechatUserQrCode(localFile, userIdentify,
            "page/index/video/video", "280");
        if (wechatQrCodeByte == null) {
            logger.error("生成会议微信二维码失败,wechatQrCodeByte--->{}", wechatQrCodeByte);
            throw new ServiceException("生成会议微信二维码失败,请重试!");
        }
        FileUploadHelp.transImageToFile(localFile, wechatQrCodeByte);
        Map<String, Object> uploadMap = FileUploadHelp.imageUpload(localFile);
        int status = Integer.valueOf(String.valueOf(uploadMap.get("status")));
        if (status == 0) {
            String message = String.valueOf(uploadMap.get("message"));
            throw new ServiceException(message);
        }
        String roomQrCodePath = String.valueOf(uploadMap.get("fileUrl"));
        //删除html文件
        if (localFile.isFile() && localFile.exists()) {
            localFile.delete();
        }
        if (StringUtils.isBlank(roomQrCodePath)) {
            logger.error("生成会议微信二维码失败,userWechatQrUrl--->{}", roomQrCodePath);
            throw new ServiceException("生成会议微信二维码失败,请重试!");
        }
        //step5:更新会议信息
        liveRoomModel.setRoomQrCodePath(roomQrCodePath);
        liveRoomModel.setPushServerUrl(pushServerUrl);
        liveRoomModel.setPushSecretKey(pushSecretKey);
        liveRoomModel.setPushExpireDate(pushExpireDate);
        liveRoomModel.setRoomCode(roomCode);
        liveRoomModel.setPullFlvUrl(pullFlvUrl);
        liveRoomModel.setPullHlsUrl(pullHlsUrl);
        liveRoomModel.setPullRtmpUrl(pullRtmpUrl);
        liveRoomModel.setPullUdpUrl(pullUdpUrl);

        this.roomDao.updateLiveInfo(liveRoomModel);
    }

    /**
     * 修改会议状态
     * @param jsonMessage
     * @throws Exception
     */
    public void updateLiveStatus(JsonMessage jsonMessage) throws Exception {
        //step1:获取请求参数
        Integer id = jsonMessage.getInteger("id");
        Integer operationType = jsonMessage.getInteger("operationType");
        if (id == null || id == 0) {
            throw new ServiceException("请求参数ID不能为空!");
        }
        if (operationType == null) {
            throw new ServiceException("请求参数操作类型不能为空!");
        }
        //step2:查询会议信息
        LiveRoomModel liveRoomModel = this.roomDao.getLiveRoomById(id);
        if (liveRoomModel == null) {
            throw new ServiceException("会议信息不存在!");
        }
        //step2.1:请求类型操作验证
        int roomStatus=liveRoomModel.getRoomStatus();
        int publishStatus=liveRoomModel.getPublishStatus();
        //step2.2:立即发布验证
        if (operationType == 1) {
            if (roomStatus != 2) {
                throw new ServiceException("会议非直播结束状态,不能发布!");
            }
            if (publishStatus == 1) {
                throw new ServiceException("会议视频已发布!");
            }
        }
        //step2.3:直播结束验证
        if (operationType == 2) {
            if (roomStatus == 2) {
                throw new ServiceException("会议视频已结束!");
            }
        }
        //step2.4:下架验证
        if (operationType == 3) {
            if (publishStatus == 2) {
                throw new ServiceException("会议视频已下架!");
            }
        }
        //step3:修改状态
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        if (operationType == 1) {
            paramMap.put("publishStatus", 1);
        }
        if (operationType == 2) {
            paramMap.put("roomStatus", 2);
            //step3.1:调用腾讯api禁推直播
            LiveClientHelp.forbidLiveStream(ApplicationConfig.tecentCloud_live_pushServerurl,
                ApplicationConfig.tecentCloud_live_appName, liveRoomModel.getRoomCode());
        }
        if (operationType == 3) {
            paramMap.put("publishStatus", 2);
        }
        this.roomDao.updateLiveStatus(paramMap);
    }

    /**
     * 修改会议信息
     * @param jsonMessage
     * @throws Exception
     */
    public void updateLive(JsonMessage jsonMessage) throws Exception {
        //step1：获取请求参数
        Integer id = jsonMessage.getInteger("id");
        String roomTitle = jsonMessage.getString("roomTitle");
        String roomPicPath = jsonMessage.getString("roomPicPath");
        String roomSchedulePath = jsonMessage.getString("roomSchedulePath");
        String roomDescPath = jsonMessage.getString("roomDescPath");
        Date startDate = DateUtil.StringToDate(jsonMessage.getString("liveStartDate"));
        String videoMp4Url = jsonMessage.getString("videoMp4Url");
        LiveRoomModel.validateUpdateLiveParam(id, roomTitle, roomPicPath, roomSchedulePath,
            roomDescPath, startDate, videoMp4Url);
        //step2:查询会议信息
        LiveRoomModel liveRoomModel = this.roomDao.getLiveRoomById(id);
        if (liveRoomModel == null) {
            throw new ServiceException("会议信息不存在!");
        }
        //step3:修改会议信息
        liveRoomModel.setRoomTitle(roomTitle);
        liveRoomModel.setRoomPicPath(roomPicPath);
        liveRoomModel.setRoomSchedulePath(roomSchedulePath);
        liveRoomModel.setRoomDescPath(roomDescPath);
        liveRoomModel.setLiveStartDate(startDate);
        liveRoomModel.setVideoMp4Url(videoMp4Url);
        this.roomDao.updateLive(liveRoomModel);
    }

}
