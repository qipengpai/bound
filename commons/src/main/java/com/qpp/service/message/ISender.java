/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    ISender.java
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
package com.qpp.service.message;

import java.util.Map;


/**
 * @author qipengpai
 * @Title: RespInfo
 * @ProjectName bound
 * @Description: TODO 发送消息面向接口
 * @date 9:44 2018/10/12
 */
public interface ISender {

    /**
     * @Author qipengpai
     * @Description //TODO 发送消息
     * @Date 16:37 2018/10/12
     * @Param [para]
     * @Throws
     * @return void
     **/
    void send(Map<String, Object> para);
    
    /**
     * @Author qipengpai
     * @Description //TODO (群发)
     * @Date 16:37 2018/10/12
     * @Param [para]
     * @Throws
     * @return void
     **/
    void sendAll(Map<String, Object> para);
}
