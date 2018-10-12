package com.qpp.model.chuanglan.request;

import lombok.Data;

/**
 * @author qipengpai
 * @Title: SmsBalanceRequest
 * @ProjectName bound
 * @Description: TODO 查询账号余额实体类
 * @date 9:34 2018/10/12
 */
@Data
public class SmsBalanceRequest {
	/**
	 * 用户账号，必填
	 */
	private String account;
	/**
	 * 用户密码，必填
	 */
	private String password;
	
	public SmsBalanceRequest() {
		
	}
	public SmsBalanceRequest(String account, String password) {
		super();
		this.account = account;
		this.password = password;
		
	}

	
	
}
