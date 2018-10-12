/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message.imp
 *
 *    Filename:    MessageServiceImp.java
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
package com.qpp.service.message.imp;

import com.qpp.dao.message.MessageMapper;
import com.qpp.service.message.IMeaageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author qipengpai
 * @Title: PushMessageAdapter
 * @ProjectName bound
 * @Description: TODO 消息管理
 * @date 9:44 2018/10/12
 */
@Service
public class MessageServiceImp implements IMeaageService {

    @Autowired
    MessageMapper messageMapper;


    /**
     * @Author qipengpai
     * @Description //TODO 持久化个推消息
     * @Date 16:59 2018/10/12
     * @Param [info]
     * @Throws
     * @return void
     **/
    @Override
    @Transactional
    public void addPushPersonMessage(Map<String, Object> info) {
        // TODO Auto-generated method stub
        messageMapper.addPushPersonMessage(info);
    }
}
