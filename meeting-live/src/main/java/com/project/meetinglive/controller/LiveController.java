package com.project.meetinglive.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.meetinglive.core.data.message.ResponseMessage;
import com.project.meetinglive.core.data.pageBean.SinglePageBean;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.exception.CommonExceptionHandle;
import com.project.meetinglive.core.spring.interceptor.annotation.LoginRequired;
import com.project.meetinglive.modal.LiveRoomModel;
import com.project.meetinglive.service.LiveService;
import com.project.meetinglive.vo.LiveListVO;

/**
 * 直播业务控制层
 * @author hejinguo
 * @version $Id: ApiAdvertController.java, v 0.1 2020年7月25日 下午12:03:02
 */
@RestController
@RequestMapping("/pc/liveService")
public class LiveController {
    private static final Logger logger = LoggerFactory.getLogger(LiveController.class);
    @Autowired
    private LiveService         liveService;

    /**
     * 获取直播列表
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getLiveList" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage getLiveList(@RequestBody JsonMessage jsonMessage,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            SinglePageBean<LiveListVO> liveList = this.liveService.getLiveList(jsonMessage);
            resMessage.addBeanList("liveList", liveList.getList() != null ? liveList.getList()
                : new ArrayList<LiveListVO>());
            resMessage.addKey$Value("currentPage", liveList.getCurrentPage());
            resMessage.addKey$Value("totalPage", liveList.getTotalPage());
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 修改讲座状态
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/updateLiveStatus" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage updateLiveStatus(@RequestBody JsonMessage jsonMessage,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            this.liveService.updateLiveStatus(jsonMessage);
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 查看会议详情
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getLiveDetail" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage getLiveDetail(@RequestBody JsonMessage jsonMessage,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            LiveRoomModel liveRoomModel = this.liveService.getLiveDetail(jsonMessage);
            //step2:返回结果
            resMessage.addKey$Value("id", liveRoomModel.getId());
            resMessage.addKey$Value("roomTitle", liveRoomModel.getRoomTitle());
            resMessage.addKey$Value("roomPicPath", liveRoomModel.getRoomPicPath());
            resMessage.addKey$Value("roomQrCodePath", liveRoomModel.getRoomQrCodePath());
            resMessage.addKey$Value("roomSchedulePath", liveRoomModel.getRoomSchedulePath());
            resMessage.addKey$Value("roomDescPath", liveRoomModel.getRoomDescPath());
            resMessage.addKey$Value("liveStartDate", liveRoomModel.getLiveStartDate());
            resMessage.addKey$Value("playNumber", liveRoomModel.getPlayNumber());
            resMessage.addKey$Value("pushServerUrl", liveRoomModel.getPushServerUrl());
            resMessage.addKey$Value("pushSecretKey", liveRoomModel.getPushSecretKey());
            resMessage.addKey$Value("pullFlvUrl", liveRoomModel.getPullFlvUrl());
            resMessage.addKey$Value("videoMp4Url", liveRoomModel.getVideoMp4Url());
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 添加会议信息
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/addLive" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage addLive(@RequestBody JsonMessage jsonMessage,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            this.liveService.addLive(jsonMessage);
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 修改讲座信息
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/updateLive" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage updateLive(@RequestBody JsonMessage jsonMessage,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            this.liveService.updateLive(jsonMessage);
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

}
