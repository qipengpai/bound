package com.qpp.model.chuanglan.response;
import lombok.Data;

/**
 * @author qipengpai
 * @Title: SmsBalanceResponse
 * @ProjectName bound
 * @Description: TODO 账号余额响应实体类
 * @date 9:34 2018/10/12
 */
@Data
public class SmsBalanceResponse {
	/**
	 * 响应时间
	 */
	private String time;
	/**
	 * 消息id
	 */
	private String balance;
	/**
	 * 状态码说明（成功返回空）
	 */
	private String errorMsg;
	/**
	 * 状态码（详细参考提交响应状态码）
	 */
	private String code;

	@Override
	public String toString() {
		return "SmsBalanceResponse [time=" + time + ", balance=" + balance + ", errorMsg=" + errorMsg + ", code=" + code
				+ "]";
	}
	
	
	
	

}
