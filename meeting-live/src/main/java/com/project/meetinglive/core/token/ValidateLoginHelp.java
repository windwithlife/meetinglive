package com.project.meetinglive.core.token;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.meetinglive.core.data.message.ResponseMessage;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.encrypt.DesPcTokenUtil;
import com.project.meetinglive.core.encrypt.DesTokenUtil;
import com.project.meetinglive.core.redis.JedisDBEnum;
import com.project.meetinglive.core.redis.JedisHelper;

/**
 * 用户登录验证
 * @author hejinguo
 * @version $Id: ValidateLoginHelp.java, v 0.1 2019年11月20日 下午1:57:02
 */
public class ValidateLoginHelp {
    private static final Logger logger = LoggerFactory.getLogger(ValidateLoginHelp.class);

    /**
     * 用户登录验证
     * @param jsonMessage
     * @return
     */
    public static ResponseMessage validateUserLogin(JsonMessage jsonMessage) {
        //返回对象
        ResponseMessage resMessage = new ResponseMessage(jsonMessage);
        //step1:登录校验
        if (jsonMessage == null) {
            resMessage.setStatus(ResponseMessage.FAILURE_CODE);
            resMessage.setMessage("请求参数不能为空!");
            return resMessage;
        }
        Integer platType = resMessage.getPlatType();
        if (platType == null) {
            resMessage.setStatus(ResponseMessage.FAILURE_CODE);
            resMessage.setMessage("请求协议格式错误,请填写platType参数!");
            logger.error("请求协议格式错误,请填写platType参数!");
            return resMessage;
        }
        if (platType == 3) {//微信
            String token = jsonMessage.getToken();
            if (StringUtils.isBlank(token)) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("您当前未登录!");
                logger.error("请求参数TokenId不能为空!");
                return resMessage;
            }
            //step2:根据token获取userId
            String tokenValue = JedisHelper.getInstance().get(token, JedisDBEnum.WECHAT);
            if (StringUtils.isBlank(tokenValue)) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("您当前未登录!");
                return resMessage;
            }
            String[] tokenValueAry = DesTokenUtil.decrypt(tokenValue).split(",");
            if (StringUtils.isBlank(tokenValueAry[0])) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("您当前未登录!");
                return resMessage;
            }
            if (!token.equals(tokenValueAry[1])) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("登录失效,请重新登录!");
                return resMessage;
            }
        } else if (platType == 4) {//pc
            String token = jsonMessage.getToken();
            if (StringUtils.isBlank(token)) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("您当前未登录!");
                logger.error("请求参数TokenId不能为空!");
                return resMessage;
            }
            String tokenValue = JedisHelper.getInstance().get(token, JedisDBEnum.PC);
            if (StringUtils.isBlank(tokenValue)) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("您当前未登录!");
                return resMessage;
            }
            String[] tokenValueAry = DesPcTokenUtil.decrypt(tokenValue).split(",");
            if (StringUtils.isBlank(tokenValueAry[0])) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("您当前未登录!");
                return resMessage;
            }
            if (!token.equals(tokenValueAry[1])) {
                resMessage.setStatus(ResponseMessage.NO_LOGIN);
                resMessage.setMessage("登录失效,请重新登录!");
                return resMessage;
            }
        } else {
            resMessage.setStatus(ResponseMessage.FAILURE_CODE);
            resMessage.setMessage("请求协议格式错误,platType参数错误!");
            logger.error("请求协议格式错误,platType参数错误!");
            return resMessage;
        }
        //已登录
        resMessage.setStatus(ResponseMessage.SUCCESS_CODE);
        return resMessage;
    }
}
