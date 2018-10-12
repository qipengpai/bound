/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    MessageAdapter.java
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


import com.qpp.service.message.imp.ChuangLanMessageProvider;
import com.qpp.service.message.imp.EucpMassageProvider;
import com.qpp.service.message.imp.SMSMessageProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author qipengpai
 * @Title: RespInfo
 * @ProjectName bound
 * @Description: TODO 借款人短信专用
 * @date 9:44 2018/10/12
 */
@Service
public class MessageAdapter {

	@Resource
	private SMSMessageProvider sMSMessageProvider;
	
	@Resource
	private ChuangLanMessageProvider chuangLanMessageProvider;

	@Resource
	private EucpMassageProvider eucpMassageProvider;
	/**
	 * @Author qipengpai
	 * @Description //TODO 发送短信验证码 BuinessType 10
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendVcode(Map<String, Object> para) {
		// TODO Auto-generated method stub
		/*para.put("cid", "nn5kdbycDmjnull");
		sMSMessageProvider.produce().send(para);*/
		//chuangLanMessageProvider.produce().send(para);
		//短信验证码
		String smsCode = para.get("p1").toString();
		//发送信息
		String msg = "【中科博润】尊敬的用户，您的手机验证码是：" + smsCode + ",2分钟内有效。（谨防受骗，请不要把此验证码泄露给任何人，非本人操作请忽略）";
		para.put("msg",msg);
		eucpMassageProvider.produce().send(para);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 用于发送提交审核短信提醒 BuinessType 11
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendAuditMessage(Map<String, Object> para) {

	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 用于发送审核通过短信通知 BuinessType 12
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendAuditPassMessage(Map<String, Object> para) {

	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 用于发送审核拒绝短信通知 BuinessType 13
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendAuditRefuseMessage(Map<String, Object> para) {

	}
	
	/**
	 * @Author qipengpai
	 * @Description //TODO 用于发送放款短信通知 BuinessType 14
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendLoanMessage(Map<String, Object> para) {

	}
	
	
	/**
	 * @Author qipengpai
	 * @Description //TODO 用于发送还款提醒短信 BuinessType 15
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendRepaymentWarnMessage(Map<String, Object> para) {

	}
	
	/**
	 * @Author qipengpai
	 * @Description //TODO 已还款通知 BuinessType 16
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendRepaymentMessage(Map<String, Object> para) {

	}
	
	/**
	 * @Author qipengpai
	 * @Description //TODO 逾期催收 BuinessType 17
	 * @Date 17:07 2018/10/12
	 * @Param [para]
	 * @Throws
	 * @return void
	 **/
	public void sendOverdueMessage(Map<String, Object> para) {

	}
	
	/**
    * @Author qipengpai
	* @Title: sendMessage
    * @Date 17:07 2018/10/12
	* @Description: TODO 定制开发短信平台实现，通过反射实现，需实现ISender接口类
	* @param @param clazz
	* @param @param para
	* @param @throws InstantiationException
	* @param @throws IllegalAccessException
	* @param @throws ClassNotFoundException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendMessage(Class<? extends ISender> clazz, Map<String, Object> para)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		eucpMassageProvider.produce(clazz).send(para);
	}
}
