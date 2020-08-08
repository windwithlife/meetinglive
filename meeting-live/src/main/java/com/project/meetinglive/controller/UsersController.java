package com.project.meetinglive.controller;

import java.util.Map;

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
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.exception.CommonExceptionHandle;
import com.project.meetinglive.core.spring.interceptor.annotation.LoginRequired;
import com.project.meetinglive.service.UsersService;

/**
 * 用户管理控制层
 * @author hejinguo
 * @version $Id: UsersController.java, v 0.1 2020年7月13日 上午11:10:13
 */
@RestController
@RequestMapping("/pc/userService")
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UsersService     usersService;

    
    /**
     * pc用户登录
     */
    @PostMapping(value = { "/userLogin" }, consumes = { "application/json" }, produces = { "application/json" })
    public @ResponseBody ResponseMessage userLogin(@RequestBody JsonMessage jsonMessage,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            //step1:pc用户登录
            Map<String, Object> resultMap = this.usersService.userLogin(jsonMessage);
            //step3:返回结果
            resMessage.addKey$Value("token", resultMap.get("token"));
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 获取主页菜单列表
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getUserMenu" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage getUserMenu(@RequestBody JsonMessage jsonMessage,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            //step1:获取主页菜单
            Map<String, Object> resultMap = this.usersService.getUserMenu(jsonMessage);
            //step3:返回结果
            resMessage.addKey$Value("id", resultMap.get("id"));
            resMessage.addKey$Value("userTrueName", resultMap.get("userTrueName"));
            resMessage.addKey$Value("headPic", resultMap.get("headPic"));
            resMessage.addKey$Value("menuList", resultMap.get("menuList"));
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 获取讲师列表
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getUserInfoList" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage getUserInfoList(@RequestBody JsonMessage jsonMessage,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {

            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 获取讲师详情
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getUserInfoDetail" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage getUserInfoDetail(@RequestBody JsonMessage jsonMessage,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {

            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 添加讲师
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/addUserInfo" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage addUserInfo(@RequestBody JsonMessage jsonMessage,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {

            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 获取讲师列表
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getInstructorList" }, consumes = { "application/json" }, produces = { "application/json" })
    @LoginRequired
    public @ResponseBody ResponseMessage getInstructorList(@RequestBody JsonMessage jsonMessage,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {

            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

}
