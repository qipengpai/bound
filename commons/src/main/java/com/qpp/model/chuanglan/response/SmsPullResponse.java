package com.qpp.model.chuanglan.response;

import lombok.Data;

import java.util.List;


/**
 * @author qipengpai
 * @Title: SmsPullRequest
 * @ProjectName bound
 * @Description: TODO 上行明细响应实体类
 * @date 9:34 2018/10/12
 */
@Data
public class SmsPullResponse {
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
		 * 上行时间
		 */
		private String moTime;
		/**
		 * 平台通道码
		 */
		private String spCode;
		/**
		 * 上行号码
		 */
		private String mobile;
		/**
		 * 运营商通道码
		 */
		private String destCode;
		/**
		 * 上行内容
		 */
		private String messageContent;

	}

	
}
