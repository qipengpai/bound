/**
 * 
 */
package com.qpp.exception;

/**
 * @author qipengpai
 * @Title: UTF8Util
 * @ProjectName bound
 * @Description: TODO 自定义异常
 * @date 13:44 2018/10/11
 */
@SuppressWarnings("serial")
public class BusinessException extends Exception {

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;
	
	public BusinessException(String code, String msgFormat){
		this.code = code;
		this.msg = msgFormat;
	}

	public BusinessException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}
}
