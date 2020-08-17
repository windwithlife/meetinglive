package com.project.meetinglive.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.meetinglive.core.data.pageBean.SinglePageBean;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.exception.ServiceException;
import com.project.meetinglive.dao.RoomDao;
import com.project.meetinglive.modal.LiveRoomModel;
import com.project.meetinglive.vo.LiveListVO;
import com.project.meetinglive.vo.LiveRoomVO;

/**
 * 直播视频业务层
 * @author hejinguo
 * @version $Id: ApiAdvertService.java, v 0.1 2020年7月25日 下午12:04:07
 */
@Service
public class ApiRoomService {
    @Autowired
    private RoomDao roomDao;

    /**
     * 获取首页直播速递
     * @return
     * @throws Exception
     */
    public List<LiveRoomVO> getLiveStartList() throws Exception {
        return this.roomDao.getLiveStartList();
    }

    /**
     * 获取直播历史信息
     * @return
     * @throws Exception
     */
    public List<LiveRoomVO> getLiveHistoryList() throws Exception {
        return this.roomDao.getLiveHistoryList();
    }

    /**
     * 获取会议列表信息
     * @param jsonMessage
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
        int totalCount = this.roomDao.getWechatLiveListCount(paramMap);
        //step4:设置分页参数信息
        response.setTotalItems(totalCount);
        response.countAllPage();
        //step5:查询广告信息
        if (totalCount > 0) {
            paramMap.put("pageSize", response.getPageSize());
            paramMap.put("startIndex", response.gainStartIndex());
            List<LiveListVO> listBanner = this.roomDao.getWechatLiveList(paramMap);
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
     * 根据房间号查询直播信息
     * @param roomCode
     * @return
     * @throws Exception
     */
    public LiveRoomModel getLiveRoomByRoomCode(String roomCode) throws Exception {
        if (StringUtils.isBlank(roomCode)) {
            throw new ServiceException("请求参数roomCode不能为空!");
        }
        return this.roomDao.getLiveRoomByRoomCode(roomCode);
    }

    /**
     * 修改直播信息
     * @param liveRoomModel
     * @throws Exception
     */
    public void updateLiveRoomInfo(LiveRoomModel liveRoomModel) throws Exception {
        this.roomDao.updateLiveRoomInfo(liveRoomModel);
    }

}
