package com.project.meetinglive.modal;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.project.meetinglive.core.exception.ServiceException;

/**
 * 直播间实体
 * @author hejinguo
 * @version $Id: LiveRoomModel.java, v 0.1 2020年7月25日 上午11:49:21
 */
public class LiveRoomModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 3159778181788952907L;

    private Integer           id;

    /**
     * 直播标题
     */
    private String            roomTitle;

    /**
     * 直播图片
     */
    private String            roomPicPath;
    /**
     * 会议二维码地址
     */
    private String            roomQrCodePath;
    /**
     * 日程图片地址
     */
    private String            roomSchedulePath;
    /**
     * 介绍图片地址
     */
    private String            roomDescPath;

    /**
     * 直播内容描述
     */
    private String            roomDesc;

    /**
     * 房间ID(确保唯一性)
     */
    private String            roomCode;

    /**
     * 推流服务器地址
     */
    private String            pushServerUrl;

    /**
     * 推流秘钥串
     */
    private String            pushSecretKey;

    /**
     * 推流过期时间
     */
    private Date              pushExpireDate;

    /**
     * RTMP拉流地址
     */
    private String            pullRtmpUrl;

    /**
     * FVL拉流地址
     */
    private String            pullFlvUrl;

    /**
     * HLSL拉流地址
     */
    private String            pullHlsUrl;

    /**
     * UDP拉流地址
     */
    private String            pullUdpUrl;

    /**
     * MP4录播地址
     */
    private String            videoMp4Url;

    /**
     * 讲师ID(users表ID)
     */
    private Integer           lecturerId;

    /**
     * 直播状态(0:未开始 1:直播中 2:已结束)
     */
    private Integer           roomStatus;

    /**
     * 直播开始时间
     */
    private Date              liveStartDate;

    /**
     * 直播结束时间
     */
    private Date              liveEndDate;

    /**
     * 是否可回放(0:不可回放 1:可回放)
     */
    private Integer           playAble;

    /**
     * 是否发布(0:未发布 1:已发布 2:已下架)
     */
    private Integer           publishStatus;

    /**
     * 播放量
     */
    private Integer           playNumber;

    /**
     * 创建时间
     */
    private Date              createdDate;

    /**
     * 创建人
     */
    private String            createdName;

    /**
     * 添加会议参数验证
     * @param roomTitle
     * @param roomPicPath
     * @param roomSchedulePath
     * @param roomDescPath
     * @param startDate
     */
    public static void validateAddLiveParam(String roomTitle, String roomPicPath,
                                            String roomSchedulePath, String roomDescPath,
                                            Date startDate) {
        if (StringUtils.isBlank(roomTitle)) {
            throw new ServiceException("会议名称不能为空!");
        }
        if (StringUtils.isBlank(roomPicPath)) {
            throw new ServiceException("封面图片不能为空!");
        }
        if (StringUtils.isBlank(roomSchedulePath)) {
            throw new ServiceException("日程图片不能为空!");
        }
        if (StringUtils.isBlank(roomDescPath)) {
            throw new ServiceException("介绍图片不能为空!");
        }
        if (startDate == null) {
            throw new ServiceException("开始时间不能为空!");
        }
    }

    /**
     * 修改会议参数验证
     * @param roomTitle
     * @param roomPicPath
     * @param roomSchedulePath
     * @param roomDescPath
     * @param startDate
     */
    public static void validateUpdateLiveParam(Integer id, String roomTitle, String roomPicPath,
                                            String roomSchedulePath, String roomDescPath,
                                               Date startDate, String videoMp4Url) {
        if (id == null || id == 0) {
            throw new ServiceException("会议ID不能为空!");
        }
        if (StringUtils.isBlank(roomTitle)) {
            throw new ServiceException("会议名称不能为空!");
        }
        if (StringUtils.isBlank(roomPicPath)) {
            throw new ServiceException("封面图片不能为空!");
        }
        if (StringUtils.isBlank(roomSchedulePath)) {
            throw new ServiceException("日程图片不能为空!");
        }
        if (StringUtils.isBlank(roomDescPath)) {
            throw new ServiceException("介绍图片不能为空!");
        }
        if (startDate == null) {
            throw new ServiceException("开始时间不能为空!");
        }
        if (StringUtils.isBlank(videoMp4Url)) {
            throw new ServiceException("录播地址不能为空!");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomPicPath() {
        return roomPicPath;
    }

    public void setRoomPicPath(String roomPicPath) {
        this.roomPicPath = roomPicPath;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getPushServerUrl() {
        return pushServerUrl;
    }

    public void setPushServerUrl(String pushServerUrl) {
        this.pushServerUrl = pushServerUrl;
    }

    public String getPushSecretKey() {
        return pushSecretKey;
    }

    public void setPushSecretKey(String pushSecretKey) {
        this.pushSecretKey = pushSecretKey;
    }

    public Date getPushExpireDate() {
        return pushExpireDate;
    }

    public void setPushExpireDate(Date pushExpireDate) {
        this.pushExpireDate = pushExpireDate;
    }

    public String getPullRtmpUrl() {
        return pullRtmpUrl;
    }

    public void setPullRtmpUrl(String pullRtmpUrl) {
        this.pullRtmpUrl = pullRtmpUrl;
    }

    public String getPullFlvUrl() {
        return pullFlvUrl;
    }

    public void setPullFlvUrl(String pullFlvUrl) {
        this.pullFlvUrl = pullFlvUrl;
    }

    public String getPullHlsUrl() {
        return pullHlsUrl;
    }

    public void setPullHlsUrl(String pullHlsUrl) {
        this.pullHlsUrl = pullHlsUrl;
    }

    public String getPullUdpUrl() {
        return pullUdpUrl;
    }

    public void setPullUdpUrl(String pullUdpUrl) {
        this.pullUdpUrl = pullUdpUrl;
    }

    public String getVideoMp4Url() {
        return videoMp4Url;
    }

    public void setVideoMp4Url(String videoMp4Url) {
        this.videoMp4Url = videoMp4Url;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Date getLiveStartDate() {
        return liveStartDate;
    }

    public void setLiveStartDate(Date liveStartDate) {
        this.liveStartDate = liveStartDate;
    }

    public Date getLiveEndDate() {
        return liveEndDate;
    }

    public void setLiveEndDate(Date liveEndDate) {
        this.liveEndDate = liveEndDate;
    }

    public Integer getPlayAble() {
        return playAble;
    }

    public void setPlayAble(Integer playAble) {
        this.playAble = playAble;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getRoomQrCodePath() {
        return roomQrCodePath;
    }

    public void setRoomQrCodePath(String roomQrCodePath) {
        this.roomQrCodePath = roomQrCodePath;
    }

    public String getRoomSchedulePath() {
        return roomSchedulePath;
    }

    public void setRoomSchedulePath(String roomSchedulePath) {
        this.roomSchedulePath = roomSchedulePath;
    }

    public String getRoomDescPath() {
        return roomDescPath;
    }

    public void setRoomDescPath(String roomDescPath) {
        this.roomDescPath = roomDescPath;
    }

}
