/**
 * DianMei.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.project.meetinglive.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.meetinglive.modal.UserDetailModel;
import com.project.meetinglive.vo.UserInfoVo;

/**
 * 用户明细管理层接口
 * @author hejinguo
 * @version $Id: PaymentRecordDao.java, v 0.1 2019年11月17日 下午9:26:50
 */
@Mapper
public interface UserDetailDao {

    /**
     * @Description: 个人中心-查询用户的详情信息
     * @Author: G on 2019/11/19 15:58:38
     * @params:
     * @return:
     */
    UserDetailModel getUserDetailInfo(int userId);

    /**
     * 修改用户所在地区
     * @author G
     * @param userDetailModel
     */
    void updateUserRegion(UserDetailModel userDetailModel);

    /**
     * 修改用户详细地址
     * @author G
     * @param userDetailModel
     */
    void updateUserAddress(UserDetailModel userDetailModel);

    /**
     * 修改用户明细信息
     * @param userInfoVo
     */
    void updateUserDetailInfo(UserInfoVo userInfoVo);

}
