/******************************************************************
 *
 *    Package:     com.company.platform.base.dao.message
 *
 *    Filename:    MessageMapper.java
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
package com.qpp.dao.message;

import java.util.Map;


/**
 * @author qipengpai
 * @Title: MessageMapper
 * @ProjectName bound
 * @Description: TODO 群发、个推消息持久化
 * @date 9:34 2018/10/12
 */
public interface MessageMapper {


    /**
     * @Author qipengpai
     * @Description //TODO 持久化个推消息
     * @Date 17:00 2018/10/12
     * @Param [info]
     * @Throws
     * @return void
     **/
    void addPushPersonMessage(Map<String, Object> info);
}
