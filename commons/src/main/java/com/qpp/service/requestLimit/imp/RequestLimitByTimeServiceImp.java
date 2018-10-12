/******************************************************************
 *
 *    Package:     com.company.platform.base.service.requestLimit.imp
 *
 *    Filename:    RequestLimitByTimeServiceImp.java
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
package com.qpp.service.requestLimit.imp;


import com.qpp.model.basic.RequestLimitByTimeModel;
import com.qpp.service.requestLimit.IRequestLimitByTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author qipengpai
 * @Title: RequestLimitByTimeServiceImp
 * @ProjectName bound
 * @Description: TODO 请求次数service
 * @date 9:44 2018/10/12
 */
@Service
public class RequestLimitByTimeServiceImp implements IRequestLimitByTimeService {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(RequestLimitByTimeServiceImp.class);

    /**
     * @Author qipengpai
     * @Description //TODO 存储请求次数
     * @Date 11:52 2018/10/12
     * @Param [requestLimitByTimeModel, key]
     * @Throws
     * @return com.qpp.model.basic.RequestLimitByTimeModel
     **/
    @Override
    @CachePut(value = "requesTimesLimit", key = "#key")
    public RequestLimitByTimeModel putRequestTimesByKey(RequestLimitByTimeModel requestLimitByTimeModel , String key) {
        // TODO Auto-generated method stub
        if(logger.isInfoEnabled()){
            logger.info("增加请求次数缓存");
        }
        return requestLimitByTimeModel;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 通过key查询请求次数
     * @Date 11:52 2018/10/12
     * @Param [key]
     * @Throws
     * @return com.qpp.model.basic.RequestLimitByTimeModel
     **/
    @Override
    @Cacheable(value = "requesTimesLimit" , key = "#key")
    public RequestLimitByTimeModel getRequestTimesByKey(String key) {
        // TODO Auto-generated method stub
        if(logger.isInfoEnabled()){
            logger.info("通过key获取请求次数缓存");
        }
        RequestLimitByTimeModel requestLimitByTimeModel = new RequestLimitByTimeModel();
        requestLimitByTimeModel.setKey(key);
        requestLimitByTimeModel.setTimes(0);
        return requestLimitByTimeModel;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 通过key清除次数
     * @Date 11:52 2018/10/12
     * @Param [key]
     * @Throws
     * @return void
     **/
    @Override
    @CacheEvict(value = "requesTimesLimit", key = "#key")
    public void delRequestTimesByKey(String key) {
        // TODO Auto-generated method stub
        if(logger.isInfoEnabled()){
            logger.info("通过key清除请求次数缓存");
        }
    }
}
