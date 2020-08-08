package com.project.meetinglive.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.meetinglive.modal.LiveRoomModel;
import com.project.meetinglive.vo.LiveDetailVO;
import com.project.meetinglive.vo.LiveListVO;
import com.project.meetinglive.vo.LiveRoomVO;

/**
 * 广告资讯数据层
 * @author hejinguo
 * @version $Id: AdvertDao.java, v 0.1 2020年7月25日 下午12:27:03
 */
@Mapper
public interface RoomDao {
    /**
     * 获取首页直播速递
     * @return
     * @throws Exception
     */
    public List<LiveRoomVO> getLiveStartList() throws Exception;

    /**
     * 获取直播历史信息
     * @return
     * @throws Exception
     */
    public List<LiveRoomVO> getLiveHistoryList() throws Exception;

    /**
     * 根据ID查询直播详情信息
     * @param id
     * @return
     */
    public LiveDetailVO getLiveDetail(@Param("id") int id);

    /**
     * 获取会议总记录数
     * @param paramMap
     * @return
     * @throws Exception
     */
    public int getLiveListCount(Map<String, Object> paramMap) throws Exception;

    /**
     * 获取会议列表 
     * @return
     * @throws Exception
     */
    public List<LiveListVO> getLiveList(Map<String, Object> paramMap) throws Exception;

    /**
     * 根据ID查询会议信息
     * @param id
     * @return
     * @throws Exception
     */
    public LiveRoomModel getLiveRoomById(@Param("id") int id) throws Exception;

    /**
     * 修改会议状态
     * @param paramMap
     * @throws Exception
     */
    public void updateLiveStatus(Map<String, Object> paramMap) throws Exception;

    /**
     * 修改会议信息
     * @param liveRoomModel
     * @throws Exception
     */
    public void updateLive(LiveRoomModel liveRoomModel) throws Exception;

    /**
     * 添加会议信息
     * @param liveRoomModel
     * @throws Exception
     */
    public void addLive(LiveRoomModel liveRoomModel) throws Exception;

    /**
     * 修改会议直播地址信息
     * @param liveRoomModel
     * @throws Exception
     */
    public void updateLiveInfo(LiveRoomModel liveRoomModel) throws Exception;

    /**
     * 获取会议总记录数(微信端)
     * @param paramMap
     * @return
     * @throws Exception
     */
    public int getWechatLiveListCount(Map<String, Object> paramMap) throws Exception;

    /**
     * 获取会议列表 (微信端)
     * @return
     * @throws Exception
     */
    public List<LiveListVO> getWechatLiveList(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 根据roomCode获取直播信息
     * @param roomCode
     * @return
     * @throws Exception
     */
    public LiveRoomModel getLiveRoomByRoomCode(String roomCode) throws Exception;

    /**
     * 修改会议信息
     * @param liveRoomModel
     * @throws Exception
     */
    public void updateLiveRoomInfo(LiveRoomModel liveRoomModel) throws Exception;
}
