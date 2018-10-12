/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    SMSSender.java
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

import com.alibaba.fastjson.JSON;

import com.qpp.model.chuanglan.request.SmsSendRequest;
import com.qpp.model.chuanglan.util.ChuangLanSmsUtil;
import com.qpp.service.message.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author qipengpai
 * @Title: SMSSender
 * @ProjectName bound
 * @Description: TODO 创蓝短信发送
 * @date 9:44 2018/10/12
 */
@Slf4j
@Service
public class ChuangLanSMSSender implements ISender {

	
	/**
	 * @Fields url : TODO(创蓝短信服务地址)
	 */
	private String url = "http://smssh1.253.com/msg/send/json";

	/**
	 * @Fields uid : TODO(创蓝账号接口UID)
	 */
	private String uid = "N6273154";

	/**
	 * @Fields pas : TODO(创蓝账号接口密码)
	 */
	private String pas = "rdwXG1o7h4f1cb";

	@Override
	@Async
	public void send(Map<String, Object> para) {
		if (log.isInfoEnabled()) {
			log.info("创蓝短信发送开始");
		}
		
		//手机号码
        String phone = para.get("mob").toString();
        //短信验证码
        String smsCode = para.get("p1").toString();
		
		String msg = "【物时光】尊敬的用户，您的手机验证码时：" + smsCode + ",2分钟内有效。（谨防受骗，请不要把此验证码泄露给任何人，非本人操作请忽略）";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest(uid, pas, msg, phone, "true");
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		System.out.println("请求JSON: " + requestJson);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(url, requestJson);
		
		System.out.println("返回JSON :" + response);
		
		log.info(response);
		
		if (log.isInfoEnabled()) {
			log.info("创蓝短信发送结束");
		}
	}

    @Override
    public void sendAll(Map<String, Object> para) {
        // TODO Auto-generated method stub
        
    }
}
