package com.project.meetinglive.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 直播信息返回数据实体
 * @author hejinguo
 * @version $Id: LiveRoomVO.java, v 0.1 2020年7月25日 下午12:10:39
 */
public class LiveRoomVO implements Serializable {
    /**  */
    private static final long serialVersionUID = 5790295049153044564L;

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
     * 直播内容描述
     */
    private String            roomDesc;
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
     * 播放量
     */
    private Integer           playNumber;
    /**
     * 讲师名称
     */
    private String            userTrueName;
    /**
     * 职位名称
     */
    private String            positionName;

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

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

}
