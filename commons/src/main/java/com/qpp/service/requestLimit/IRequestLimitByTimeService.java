/******************************************************************
 *
 *    Package:     com.company.platform.base.service.requestLimit.imp
 *
 *    Filename:    IRequestLimitByTime.java
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
package com.qpp.service.requestLimit;


import com.qpp.model.basic.RequestLimitByTimeModel;

/**
 * @author qipengpai
 * @Title: IRequestLimitByTimeService
 * @ProjectName bound
 * @Description: TODO 请求次数service
 * @date 9:44 2018/10/12
 */
public interface IRequestLimitByTimeService {


    /**
     * @Author qipengpai
     * @Description //TODO (存储请求次数)
     * @Date 11:47 2018/10/12
     * @Param [requestLimitByTimeModel, key]
     * @Throws
     * @return com.qpp.model.basic.RequestLimitByTimeModel
     **/
    RequestLimitByTimeModel putRequestTimesByKey(RequestLimitByTimeModel requestLimitByTimeModel, String key);


    /**
     * @Author qipengpai
     * @Description //TODO (通过key查询请求次数)
     * @Date 11:47 2018/10/12
     * @Param [key]
     * @Throws
     * @return com.qpp.model.basic.RequestLimitByTimeModel
     **/
    RequestLimitByTimeModel getRequestTimesByKey(String key);


    /**
     * @Author qipengpai
     * @Description //TODO (通过key清除次数)
     * @Date 11:48 2018/10/12
     * @Param [key]
     * @Throws
     * @return void
     **/
    void delRequestTimesByKey(String key);
}
