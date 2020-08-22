package com.project.meetinglive.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.project.meetinglive.common.util.EmojiFilterUtil;
import com.project.meetinglive.common.util.TokenProccessor;
import com.project.meetinglive.common.util.WechatUtil;
import com.project.meetinglive.core.data.message.ResponseMessage;
import com.project.meetinglive.core.data.request.JsonMessage;
import com.project.meetinglive.core.encrypt.DesPcTokenUtil;
import com.project.meetinglive.core.encrypt.DesTokenUtil;
import com.project.meetinglive.core.exception.ServiceException;
import com.project.meetinglive.core.redis.JedisDBEnum;
import com.project.meetinglive.core.redis.JedisHelper;
import com.project.meetinglive.core.token.UserTokenHelp;
import com.project.meetinglive.dao.UserDetailDao;
import com.project.meetinglive.dao.UsersDao;
import com.project.meetinglive.modal.UserDetailModel;
import com.project.meetinglive.modal.UsersModel;
import com.project.meetinglive.vo.MenuTreeVO;
import com.project.meetinglive.vo.NodeVO;

/**
 * 用户管理业务层
 * @author hejinguo
 * @version $Id: UsersService.java, v 0.1 2020年7月25日 下午3:47:56
 */
@Service
public class UsersService {
    private static final Logger     logger = LoggerFactory.getLogger(UsersService.class);
    @Autowired
    private UsersDao                usersDao;
    @Autowired
    private UserDetailDao           userDetailDao;

    /**
     * 用户登录或注册
     * @param jsonMessage
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> registerUser(JsonMessage jsonMessage) throws Exception {
        //返回信息
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //step1:获取全部请求参数并验证
        String userNickName = EmojiFilterUtil.decode(jsonMessage.getString("userNickName"));
        String headPic = jsonMessage.getString("headPic");
        Byte userGrenderWx = jsonMessage.getByte("userGrenderWx");
        String userCountryWx = jsonMessage.getString("userCountryWx");
        String userProvinceWx = jsonMessage.getString("userProvinceWx");
        String userCityWx = jsonMessage.getString("userCityWx");
        String encryptedData = jsonMessage.getString("encryptedData");
        String iv = jsonMessage.getString("iv");
        String openId = jsonMessage.getString("openId");
        String unionId = jsonMessage.getString("unionId");
        String fromUserIdentity = jsonMessage.getString("fromUserIdentity");
        logger.error("fromUserIdentity--->{}",fromUserIdentity);
        UsersModel.validateRegistUserParam(userNickName, headPic, userGrenderWx, encryptedData, iv,
            openId);
        //step2:获取手机号授权信息
        String sessionKey = JedisHelper.getInstance().get(openId, JedisDBEnum.WECHAT);
        if (StringUtils.isBlank(sessionKey)) {
            logger.error("微信授权登录获取信息会话秘钥为空,参数信息:sessionKey--->{}", sessionKey);
            throw new ServiceException("微信授权登录获取信息失败,请重新授权登录!");
        }
        JSONObject jsonObj = WechatUtil.decrypt(encryptedData, sessionKey, iv);
        String userMobile = jsonObj.getString("phoneNumber");
        if (StringUtils.isBlank(userMobile)) {
            logger.error("微信授权登录获取授权手机号为空,参数信息:userMobile--->{}", userMobile);
            throw new ServiceException("微信授权登录获取信息失败,请重新授权登录!");
        }
        //请求带有token，则直接清除
        String token = jsonMessage.getToken();
        if (StringUtils.isBlank(token)) {
            JedisHelper.getInstance().del(token, JedisDBEnum.WECHAT);
        }
        //step3:根据手机号查询用户信息
        UsersModel usersModel = this.usersDao.selectUserByUserMobile(userMobile);
        if (usersModel != null) {//重新生成token信息
            //验证用户状态
            byte userStatus = usersModel.getUserStatus();
            int userId = usersModel.getUserId();
            if (userStatus == 1) {
                logger.error("微信授权登录用户非正常状态,参数信息:userId--->{},userStatus--->{}", 
                    userId, userStatus);
                throw new ServiceException("当前账户非正常状态!");
            }
            String oldToken = usersModel.getUserToken();
            //删除旧token
            if (StringUtils.isNotBlank(oldToken)) {
                JedisHelper.getInstance().del(oldToken, JedisDBEnum.WECHAT);
            }
            //新token以及加密值
            String newToken = TokenProccessor.getInstance().makeToken();
            String value = DesTokenUtil.encrypt(usersModel.getUserId() + "," + newToken);
            //step4:修改用户资料信息
            Map<String, Object> userParamMap = new HashMap<String, Object>();
            userParamMap.put("userId", userId);
            userParamMap.put("openId", openId);
            userParamMap.put("userToken", newToken);
            this.usersDao.updateUserToken(userParamMap);

            Map<String, Object> usersMap = new HashMap<String, Object>();
            usersMap.put("userId", userId);
            usersMap.put("headPic", headPic);
            usersMap.put("userNickName", userNickName);
            this.usersDao.updateUserNickName(usersMap);
            //step5:存入到redis
            JedisHelper.getInstance().set(newToken, value, JedisDBEnum.WECHAT);
            //step6:返回信息
            returnMap.put("openId", openId);
            returnMap.put("token", newToken);
            returnMap.put("newUserMessage", "登录成功！");
        } else {
            //step4:创建用户资料信息
            String userToken = TokenProccessor.getInstance().makeToken();//用户token
            UsersModel user = UsersModel.createUsersModel(userNickName, userMobile, headPic,
                userToken,unionId, openId);
            this.usersDao.insertUsers(user);
            //step5:创建用户明细
            UserDetailModel userDetail = UserDetailModel.createUserDetailModel(user.getUserId(),
                userGrenderWx, userCountryWx, userProvinceWx, userCityWx, userMobile);
            this.usersDao.insertUserDetail(userDetail);
            //step8:存入到redis
            String value = DesTokenUtil.encrypt(user.getUserId() + "," + userToken);
            JedisHelper.getInstance().set(userToken, value, JedisDBEnum.WECHAT);
            //step9:返回信息
            returnMap.put("openId", openId);
            returnMap.put("token", userToken);
            returnMap.put("isNewUser", 1);
            returnMap.put("newUserMessage", "登录成功！");
        }
        return returnMap;
    }

    /**
     * 获取用户信息
     * @param responseMessage
     * @return
     * @throws Exception
     */
    public UsersModel getUserInfo(ResponseMessage responseMessage) throws Exception {
        String token = responseMessage.getToken();
        if (StringUtils.isBlank(token)) {
            throw new ServiceException("未获取到用户token信息");
        }
        int userId = UserTokenHelp.getPcUserId(token);
        UsersModel usersModel = usersDao.getUsersModel(userId);
        if (org.springframework.util.StringUtils.isEmpty(usersModel)) {
            throw new ServiceException("未查询到用户信息");
        }
        return usersModel;
    }

    /**
     * 用户退出登录
     * @param jsonMessage
     * @throws Exception
     */
    public void loginOut(JsonMessage jsonMessage) throws Exception {
        String openId = jsonMessage.getOpenId();
        String token = jsonMessage.getToken();
        if (StringUtils.isBlank(token)) {
            throw new ServiceException("未获取到用户的token信息");
        }
        JedisHelper.getInstance().del(token, JedisDBEnum.WECHAT);
    }

    /**
     * pc用户登录
     * @param jsonMessage
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> userLogin(JsonMessage jsonMessage) throws Exception {
        //返回信息
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //step1:获取全部请求参数并验证
        String userName = jsonMessage.getString("userName");
        String passWord = jsonMessage.getString("userPassword");
        UsersModel.validateUserLoginParam(userName, passWord);
        //step2:根据登录名查询用户信息
        UsersModel usersModel = this.usersDao.selectUserByUserLoginName(userName);
        if (usersModel == null) {
            logger.error("用户信息不存在,参数信息:userName--->{}", userName);
            throw new ServiceException("用户信息不存在!");
        }
        String userPassWord=usersModel.getPassWord();
        if (!DesPcTokenUtil.encrypt(passWord).equals(userPassWord)) {
            logger.error("用户登录密码错误,参数信息:userName--->{}", userName);
            throw new ServiceException("登录密码错误!");
        }
        if (usersModel.getUserType() != 2) {
            logger.error("该账户非系统账户,无法登录,参数信息:userName--->{}", userName);
            throw new ServiceException("该账户非系统账户,无法登录!");
        }
        //step3:重新生成新Token
        String oldToken = usersModel.getUserToken();
        //删除旧token
        if (StringUtils.isNotBlank(oldToken)) {
            JedisHelper.getInstance().del(oldToken, JedisDBEnum.PC);
        }
        //新token以及加密值
        String newToken = TokenProccessor.getInstance().makeToken();
        String value = DesPcTokenUtil.encrypt(usersModel.getUserId() + "," + newToken);
        //step4:修改用户资料信息
        Map<String, Object> userParamMap = new HashMap<String, Object>();
        userParamMap.put("userId", usersModel.getUserId());
        userParamMap.put("userToken", newToken);
        this.usersDao.updateUserToken(userParamMap);
        //step5:存入到redis
        JedisHelper.getInstance().set(newToken, value, JedisDBEnum.PC);
        //step6:返回信息
        returnMap.put("token", newToken);
        returnMap.put("newUserMessage", "登录成功！");
        return returnMap;
    }

    /**
     * 获取主页菜单列表
     * @param jsonMessage
     * @return
     * @throws Exception
     */
    public Map<String, Object> getUserMenu(JsonMessage jsonMessage) throws Exception {
        //返回对象
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //step1:获取用户ID
        int userId = UserTokenHelp.getPcUserId(jsonMessage.getToken());
        //step2:查询用户信息
        UsersModel usersModel = this.usersDao.getUsersModel(userId);
        UsersModel.validateUserStatus(usersModel);
        //step3:查询菜单信息
        Long id = 1L;//功能树顶级ID
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("isUnable", 1);//是否查询出已被禁用的功能 0：否 1：是
        //根据员工权限获取员工功能
        List<NodeVO> functions = this.usersDao.selectAllMenuById(map);
        //根据ID查询当前功能
        NodeVO node = this.usersDao.selectMenuById(id);
        //封装菜单树json
        MenuTreeVO tree = new MenuTreeVO();
        tree.getTree(functions, node, false);
        //step4:返回信息
        returnMap.put("id", usersModel.getUserId());
        returnMap.put("userTrueName", usersModel.getUserTrueName());
        returnMap.put("headPic", usersModel.getHeadPic());
        returnMap.put("menuList", tree.modifyStr(tree.returnStr.toString()));
        return returnMap;
    }

}
