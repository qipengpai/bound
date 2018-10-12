/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message.imp
 *
 *    Filename:    XingePushSender.java
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


import com.alibaba.fastjson.JSONObject;
import com.qpp.dao.loginAssist.LoginAssistMapper;
import com.qpp.service.message.IMeaageService;
import com.qpp.service.message.ISender;
import com.qpp.utils.common.DateUtil;
import com.tencent.xinge.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author qipengpai
 * @Title: XingePushSender
 * @ProjectName bound
 * @Description: TODO 信鸽推送
 * @date 9:34 2018/10/12
 */
@Slf4j
@Service
public class XingePushSender implements ISender {

    @Autowired
    LoginAssistMapper loginAssistMapper;

    @Resource
    IMeaageService messageServiceImp;

    

    private static final String ACCESS_ID_IOS = "2200270258";
    //private static final String ACCESS_KEY_IOS = "IU58374BKMRD";
    private static final String SECRET_KEY_IOS = "11097147be13e1d6654718091418b387";

    private static final String ACCESS_ID_ANDROID = "2100273323";
    // private static final String ACCESS_KEY_ANDROID = "A62NX1J6SW2J";
    private static final String SECRET_KEY_ANDROID = "6d58f5f88cfa5006e4802c07c7658ca7";

    /*
     * (非 Javadoc) <p>Title: send</p> <p>Description: </p>
     * @param para
     * @see com.company.platform.base.service.message.ISender#send(java.util.Map)
     */
    @Override
    @Async
    public void send(Map<String, Object> para) {
        // TODO Auto-generated method stub
        String messageDetail = (String) para.get("messageDetail");
        String userName = (String) para.get("userName");
        String title = para.get("title") == null ? "中科博润" : (String) para.get("title");
        if (StringUtils.isEmpty(userName)) {
            log.error("信鸽消息推送，userName为空，拒绝推送");
            return;
        }
        if (StringUtils.isEmpty(messageDetail)) {
            log.error("信鸽消息推送，推送内容为空，拒绝推送");
            return;
        }
        HashMap<String, Object> map = loginAssistMapper.getLoginRecordSelectOnebyUserName(userName);
        if (map == null) {
            log.error("信鸽消息推送，没有查询到" + userName + "账号的token和type信息，拒绝推送");
            return;
        }
        String type = StringUtils.isEmpty((String) map.get("appType")) ? "" : (String) map.get("appType");//1 安卓   2  IOS
        String token = StringUtils.isEmpty((String) map.get("token")) ? "" : (String) map.get("token");
        if ("1".equals(type) && !StringUtils.isEmpty(token)) {
            //安卓推送
            XingeApp appPush = new XingeApp(Long.parseLong(ACCESS_ID_ANDROID), SECRET_KEY_ANDROID);
            Message mess = new Message();
            mess.setTitle(title);
            mess.setContent(messageDetail);
            mess.setType(Message.TYPE_NOTIFICATION);
            mess.setMultiPkg(1);
            mess.setStyle(new Style(0, 1, 1, 0, 0));
            org.json.JSONObject ret2 = appPush.pushSingleDevice(token, mess);
            log.info("信鸽个推结果报文(安卓)：" + ret2.toString());
        } else if ("2".equals(type) && !StringUtils.isEmpty(token)) {
            //苹果推送
            XingeApp appPush = new XingeApp(Long.parseLong(ACCESS_ID_IOS), SECRET_KEY_IOS);
            MessageIOS messIOS = new MessageIOS();
            messIOS.setExpireTime(0);
            messIOS.setAlert(messageDetail);
            messIOS.setBadge(1);
            messIOS.setSound("beep.wav");
            TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
            messIOS.addAcceptTime(acceptTime);
            org.json.JSONObject ret2 = appPush.pushSingleDevice(token, messIOS, XingeApp.IOSENV_DEV);
            log.info("信鸽个推结果报文(IOS)：" + ret2.toString());
        } else {
            log.error("信鸽消息推送，app类型错误，拒绝推送");
            return;
        }
        //持久化消息
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("id", UUID.randomUUID().toString());
        info.put("userId", map.get("userId") == null ? "" : (String) map.get("userId"));
        info.put("messageType", "99");
        info.put("messageTitle", title);
        info.put("messageContent", messageDetail);
        info.put("sendDate", DateUtil.format(new Date()));
        messageServiceImp.addPushPersonMessage(info);
    }

    /*
     * (非 Javadoc) <p>Title: sendAll</p> <p>Description: </p>
     * @param para
     * @see com.company.platform.base.service.message.ISender#sendAll(java.util.Map)
     */
    @Override
    @Async
    public void sendAll(Map<String, Object> para) {
        String messageDetail = (String) para.get("messageDetail");
        String title = para.get("title") == null ? "中科博润" : (String) para.get("title");
        if (StringUtils.isEmpty(messageDetail)) {
            log.error("信鸽消息推送，推送内容为空，拒绝推送");
            return;
        }
        // TODO Auto-generated method stub

        XingeApp appPush = new XingeApp(Long.parseLong(ACCESS_ID_ANDROID), SECRET_KEY_ANDROID);
        Message mess = new Message();
        mess.setTitle(title);
        mess.setContent(messageDetail);
        mess.setType(Message.TYPE_NOTIFICATION);
        mess.setMultiPkg(1);
        mess.setStyle(new Style(0, 1, 1, 0, 0));
        org.json.JSONObject ret = new org.json.JSONObject();
        ret = appPush.pushAllDevice(0, mess);
        log.info("信鸽广播推送结果报文(安卓)：" + ret.toString());

        XingeApp appPushIOS = new XingeApp(Long.parseLong(ACCESS_ID_IOS), SECRET_KEY_IOS);
        MessageIOS messIOS = new MessageIOS();
        messIOS.setExpireTime(86400);
        messIOS.setAlert(messageDetail);
        messIOS.setBadge(1);
        messIOS.setSound("beep.wav");
        TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
        messIOS.addAcceptTime(acceptTime);
        org.json.JSONObject retIOS = new org.json.JSONObject();
        retIOS = appPushIOS.pushAllDevice(0, messIOS, XingeApp.IOSENV_DEV);
        log.info("信鸽广播推送结果报文(IOS)：" + retIOS.toString());
    }
}
