package com.project.meetinglive.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.meetinglive.api.service.ApiUsersService;
import com.project.meetinglive.api.service.ApiWechatService;
import com.project.meetinglive.core.data.message.ResponseMessage;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.exception.CommonExceptionHandle;
import com.project.meetinglive.core.wechat.AdvancedUtil;

/**
 * 微信管理控制层
 * @author hejinguo
 * @version $Id: WechatController.java, v 0.1 2020年7月13日 上午11:10:43
 */
@RestController
@RequestMapping("api/wechatService")
public class ApiWechatController {
    private static final Logger logger = LoggerFactory.getLogger(ApiWechatController.class);
    @Autowired
    private ApiWechatService    apiWechatService;
    @Autowired
    private ApiUsersService     apiUsersService;

    /**
     * 根据code获取微信openId
     * @param request
     * @param jsonMessage
     * @return
     */
    @PostMapping(value = { "/getWechatOpenId" }, consumes = { "application/json" }, produces = { "application/json" })
    public @ResponseBody ResponseMessage getWechatOpenId(HttpServletRequest request,
                                                         @RequestBody JsonMessage jsonMessage) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        //step1:参数验证
        String code = jsonMessage.getString("code");
        if (StringUtils.isBlank(code)) {
            resMessage.setMessage("微信code不能为空!");
            return resMessage;
        }
        try {
            //step2:获取openId
            String openId = this.apiWechatService.getWechatOpenId(code);
            //step3:返回结果
            resMessage.addKey$Value("openId", openId);
            resMessage.setMessage("获取微信openId成功!");
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }

    /**
     * 手机号授权登录或注册
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/registerUser" }, consumes = { "application/json" }, produces = { "application/json" })
    public @ResponseBody ResponseMessage registerUser(@RequestBody JsonMessage jsonMessage,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            //step1:用户注册或登录
            Map<String, Object> resultMap = this.apiUsersService.registerUser(jsonMessage);
            //step3:返回结果
            resMessage.addKey$Value("openId", resultMap.get("openId"));
            resMessage.addKey$Value("token", resultMap.get("token"));
            resMessage.addKey$Value("isNewUser", resultMap.get("isNewUser"));
            resMessage.addKey$Value("newUserMessage", resultMap.get("newUserMessage"));
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }
    
    /**
     * 获取微信用户授权url
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/getWechatPublicOauthUrl" }, consumes = { "application/json" }, produces = { "application/json" })
    public @ResponseBody ResponseMessage getWechatOauthUrl(@RequestBody JsonMessage jsonMessage,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        //step  1:获取请求参数
        String href = jsonMessage.getString("href");
        if (StringUtils.isBlank(href)) {
            resMessage.setMessage("请求参数href不能为空!");
            return resMessage;
        }
        //step  2:获取微信授权请求url地址
        String url = AdvancedUtil.getOauth2AccessTokenUrl("snsapi_userinfo", href);
        resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
        resMessage.addKey$Value("oauthUrl", url);
        return resMessage;
    }
    
    /**
     * 微信公众号授权登录或注册
     * @param jsonMessage
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = { "/registerWechatPublicUser" }, consumes = { "application/json" }, produces = { "application/json" })
    public @ResponseBody ResponseMessage registerWechatPublicUser(@RequestBody JsonMessage jsonMessage,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        try {
            //step1:用户注册或登录
            Map<String, Object> resultMap = this.apiUsersService.registerWechatPublicUser(jsonMessage);
            //step3:返回结果
            resMessage.addKey$Value("openId", resultMap.get("openId"));
            resMessage.addKey$Value("token", resultMap.get("token"));
            resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
            resMessage.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            CommonExceptionHandle.handleException(resMessage, jsonMessage, request, e);
        }
        return resMessage;
    }
}
