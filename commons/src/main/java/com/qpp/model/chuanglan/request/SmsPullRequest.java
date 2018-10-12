package com.qpp.model.chuanglan.request;

import lombok.Data;

/**
 * @author qipengpai
 * @Title: SmsPullRequest
 * @ProjectName bound
 * @Description: TODO 消查询上行短信实体类
 * @date 9:34 2018/10/12
 */
@Data
public class SmsPullRequest {
	/**
	 * 用户账号，必填
	 */
	private String account;
	/**
	 * 用户密码，必填
	 */
	private String password;
	/**
	 * 拉取个数（最大100，默认20），选填
	 */
	private String count;
	
	public SmsPullRequest() {
		
	}
	public SmsPullRequest(String account, String password,String count) {
		super();
		this.account = account;
		this.password = password;
		this.count = count;
		
	}


	
	
}
