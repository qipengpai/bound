package com.qpp.model.chuanglan.response;

import lombok.Data;

import java.util.List;


/**
 * @author qipengpai
 * @Title: SmsReportResponse
 * @ProjectName bound
 * @Description: TODO 状态报告响应实体类
 * @date 9:34 2018/10/12
 */
@Data
public class SmsReportResponse {
	/**
	 * 请求状态
	 */
	private String ret;
	/**
	 * 请求错误描述
	 */
	private String error;
	/**
	 * 上行明细结果
	 */
	private List<Result> result;
	
 
	@Data
	static class Result{
		/**
		 * 消息ID
		 */
		private String msgId;
		/**
		 * 状态更新时间
		 */
		private String reportTime;
		/**
		 * 接收短信的手机号码
		 */
		private String mobile;
		/**
		 * 状态（详细参考状态报告状态码）
		 */
		private String status;
		/**
		 * 状态说明
		 */
		private String statusDesc;
		/**
		 * 拉取个数
		 */
		private String count;
	}

}
