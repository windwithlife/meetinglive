package com.project.meetinglive.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.meetinglive.api.service.ApiRoomService;
import com.project.meetinglive.common.util.DateUtil;
import com.project.meetinglive.modal.LiveRoomModel;
import com.project.meetinglive.vo.RecordLiveVO;
import com.project.meetinglive.vo.SnapshotLiveVO;
import com.project.meetinglive.vo.StreamLiveVO;

/**
 * 腾讯直播回调事件处理
 * @author hejinguo
 * @version $Id: ApiTecentCloudController.java, v 0.1 2020年8月7日 上午3:30:06
 */
@RestController
@RequestMapping("api/tecentService")
public class ApiTecentCloudController {
    private static final Logger logger = LoggerFactory.getLogger(ApiTecentCloudController.class);
    @Autowired
    private ApiRoomService      apiRoomService;

    /**
     * 推流事件通知
     * @param request
     * @param response
     */
    @PostMapping(value = { "/streamBeginCallback" })
    public void streamBeginCallback(HttpServletRequest request, HttpServletResponse response,
                                    @RequestBody StreamLiveVO streamLiveVO) {
        logger.error("腾讯推流回调事件通知,参数信息--->{}", streamLiveVO.toString());
        if (streamLiveVO != null) {
            Integer event_type = streamLiveVO.getEvent_type();
            String channel_id = streamLiveVO.getChannel_id();
            if (event_type == 1) {//推流
                //step1:根据room_code查询直播信息
                try {
                    LiveRoomModel liveRoomModel = this.apiRoomService
                        .getLiveRoomByRoomCode(channel_id);
                    if (liveRoomModel == null) {
                        logger.error("腾讯推流回调事件通知时查询直播信息不存在,参数信息:roomCode--->{}", channel_id);
                        return;
                    }
                    //step2:修改直播状态为-->直播中
                    liveRoomModel.setRoomStatus(1);
                    liveRoomModel.setLiveStartDate(DateUtil.getDateToday());
                    this.apiRoomService.updateLiveRoomInfo(liveRoomModel);
                } catch (Exception e) {
                    logger.error("腾讯推流回调事件通知时查询直播信息异常,异常信息:roomCode--->{},errorMessage",
                        channel_id, e);
                }
            } else {
                logger.error("腾讯推流回调事件通知时通知类型错误,参数信息:roomCode--->{},event_type--->{}", channel_id,
                    event_type);
            }
        }
    }

    /**
     * 断流事件通知
     * @param request
     * @param response
     */
    @PostMapping(value = { "/streamEndCallback" })
    public void streamEndCallback(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody StreamLiveVO streamLiveVO) {
        logger.error("腾讯断流回调事件通知,参数信息--->{}", streamLiveVO.toString());
        if (streamLiveVO != null) {
            Integer event_type = streamLiveVO.getEvent_type();
            String channel_id = streamLiveVO.getChannel_id();
            if (event_type == 0) {//断流
                //step1:根据room_code查询直播信息
                try {
                    LiveRoomModel liveRoomModel = this.apiRoomService
                        .getLiveRoomByRoomCode(channel_id);
                    if (liveRoomModel == null) {
                        logger.error("腾讯断流回调事件通知时查询直播信息不存在,参数信息:roomCode--->{}", channel_id);
                        return;
                    }
                    //step2:修改直播状态为-->直播结束
                    liveRoomModel.setRoomStatus(2);
                    liveRoomModel.setLiveEndDate(DateUtil.getDateToday());
                    this.apiRoomService.updateLiveRoomInfo(liveRoomModel);
                } catch (Exception e) {
                    logger.error("腾讯断流回调事件通知时查询直播信息异常,异常信息:roomCode--->{},errorMessage",
                        channel_id, e);
                }
            } else {
                logger.error("腾讯断流回调事件通知时通知类型错误,参数信息:roomCode--->{},event_type--->{}", channel_id,
                    event_type);
            }
        }
    }

    /**
     * 录制事件通知
     * @param request
     * @param response
     */
    @PostMapping(value = { "/recordCallback" })
    public void recordCallback(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody RecordLiveVO recordLiveVO) {
        logger.error("腾讯录制回调事件通知,参数信息--->{}", recordLiveVO.toString());
        if (recordLiveVO != null) {
            Integer event_type = recordLiveVO.getEvent_type();
            String channel_id = recordLiveVO.getChannel_id();
            if (event_type == 100) {//录制
                //step1:根据room_code查询直播信息
                try {
                    LiveRoomModel liveRoomModel = this.apiRoomService
                        .getLiveRoomByRoomCode(channel_id);
                    if (liveRoomModel == null) {
                        logger.error("腾讯录制回调事件通知时查询直播信息不存在,参数信息:roomCode--->{}", channel_id);
                        return;
                    }
                    //step2:设置录制url地址
                    liveRoomModel.setVideoMp4Url(recordLiveVO.getVideo_url());
                    this.apiRoomService.updateLiveRoomInfo(liveRoomModel);
                } catch (Exception e) {
                    logger.error("腾讯录制回调事件通知时查询直播信息异常,异常信息:roomCode--->{},errorMessage",
                        channel_id, e);
                }
            } else {
                logger.error("腾讯录制回调事件通知时通知类型错误,参数信息:roomCode--->{},event_type--->{}", channel_id,
                    event_type);
            }
        }

    }

    /**
     * 截图事件通知
     * @param request
     * @param response
     */
    @PostMapping(value = { "/snapshotCallback" })
    public void snapshotCallback(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody SnapshotLiveVO snapshotLiveVO) {
        logger.error("腾讯截图回调事件通知,参数信息--->{}", snapshotLiveVO.toString());
    }

    /**
     * 鉴黄通知
     * @param request
     * @param response
     */
    @PostMapping(value = { "/pornCensorshipCallback" })
    public void pornCensorshipCallback(HttpServletRequest request, HttpServletResponse response) {

    }

}
