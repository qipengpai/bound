/******************************************************************
 *
 *    Package:     com.company.platform.base.dao.loginAssist
 *
 *    Filename:    LoginAssistMapper.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.qpp.dao.loginAssist;

import java.util.HashMap;
import java.util.Map;


/**
 * @author qipengpai
 * @Title: LoginAssistMapper
 * @ProjectName bound
 * @Description: TODO 添加APP登录记录
 * @date 9:34 2018/10/12
 */
public interface LoginAssistMapper {

    /**
     * @Author qipengpai
     * @Description //TODO 添加APP登录记录
     * @Date 17:04 2018/10/12
     * @Param [info]
     * @Throws
     * @return void
     **/
    void addLoginRecord(Map<String, Object> info);

    /**
     * @Author qipengpai
     * @Description //TODO 根据用户名获取最后一次登录信息
     * @Date 17:04 2018/10/12
     * @Param [userName]
     * @Throws
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     **/
    HashMap<String , Object> getLoginRecordSelectOnebyUserName(String userName);
    /**
     * @Author qipengpai
     * @Description //TODO 根据用户ID获取登录失败信息
     * @Date 17:05 2018/10/12
     * @Param [userId]
     * @Throws
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     **/
    HashMap<String , Object> getLoginFailureInfo(String userId);

    /**
     * @Author qipengpai
     * @Description //TODO 根据ID更新登录失败信息
     * @Date 17:05 2018/10/12
     * @Param [info]
     * @Throws
     * @return void
     **/
    void updateLoginFailureInfo(Map<String, Object> info);

    /**
     * @Author qipengpai
     * @Description //TODO 添加登录失败信息
     * @Date 17:05 2018/10/12
     * @Param [info]
     * @Throws
     * @return void
     **/
    void addLoginFailureInfo(Map<String, Object> info);
    
    /**
     * @Author qipengpai
     * @Description //TODO 更新系统用户锁定状态
     * @Date 17:05 2018/10/12
     * @Param [id]
     * @Throws
     * @return void
     **/
    void updateSysUserLocked(String id);
}
