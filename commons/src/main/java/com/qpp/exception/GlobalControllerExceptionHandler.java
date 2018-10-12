/******************************************************************
 *
 *    Package:     com.company.platform.exception
 *
 *    Filename:    GlobalExceptionHandler.java
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
package com.qpp.exception;

import com.alibaba.fastjson.JSONObject;

import com.qpp.baseenum.GLOBALCONFIG;
import com.qpp.baseenum.ResponseConstants;
import com.qpp.baseenum.ResponseConstantsErrorMessage;
import com.qpp.exception.children.UserTokenException;
import com.qpp.model.result.RespInfo;
import com.qpp.utils.file.GzippedOutputStreamWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(全局异常处理)
 * @author zhengjn
 * @date 2017年9月24日 下午12:42:29
 */
@Slf4j
@ControllerAdvice
@SuppressWarnings("all")
public class GlobalControllerExceptionHandler {

	
	/** 
	* @Title: errorHandler 
	* @Description: TODO(系统异常处理) 
	* @param @param e
	* @param @return    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@ExceptionHandler({ Exception.class, Throwable.class })
	public void errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		String requestUrl = request.getRequestURI();// 获取请求路径
		RespInfo respInfo = RespInfo.requestError(ResponseConstants.FAIL.getRetCode(),
				ResponseConstantsErrorMessage.getErrorMessage(requestUrl));
		log.error(respInfo.toJSONString());
		log.error(e.getMessage());
		e.printStackTrace();
		this.returnMessage(request, response, respInfo);
	}

	/** 
	* @Title: validateErrorHandler 
	* @Description: TODO(数据准入常规校验异常，如：长度、邮件规则等) 
	* @param @param e
	* @param @return    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public void validateErrorHandler(HttpServletRequest request, HttpServletResponse response,
                                     MethodArgumentNotValidException e) {
        RespInfo respInfo = null;
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult.hasErrors()) {
			List<FieldError> errorList = bindingResult.getFieldErrors();
			StringBuffer errorMsg = new StringBuffer();
			for (FieldError fieldError : errorList) {
				String str = fieldError.getField();
				if (str.indexOf("[") > 0) {
					int indexStart = str.indexOf("[");
					int indexEnd = str.indexOf("]");
					String num = str.substring(indexStart + 1, indexEnd);
					String objStr = str.substring(0, indexStart);
					if ("customerRelationshipInfo".equals(objStr) || "corporationCustomerContactinfo".equals(objStr)) {
						objStr = "联系人信息";
					} else if ("corporationCustomerShareholderInfo".equals(objStr)) {
						objStr = "股东信息";
					} else {
						objStr = "";
					}
					if (!"".equals(objStr)) {
						objStr = objStr + "第" + (new Integer(num) + 1) + "条信息中";
					}
					errorMsg.append(objStr + fieldError.getDefaultMessage());
				} else {
					errorMsg.append(fieldError.getDefaultMessage());
				}
			}
            respInfo = RespInfo.requestError(ResponseConstants.FIELD_VALIDATE_ERROR.getRetCode(),
					errorMsg.toString());
		}
		if (null == respInfo) {
            respInfo = RespInfo.requestError(ResponseConstants.FIELD_VALIDATE_ERROR.getRetCode(),
					"");
		}
		/*if (respInfo != null) {
            respInfo.createSign();
		}*/
		this.returnMessage(request, response, respInfo);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 系统自定义异常处理
	 * @Date 10:01 2018/10/12
	 * @Param [request, response, e]
	 * @Throws
	 * @return void
	 **/
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public void CustomErrorHandler(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
        RespInfo respInfo = RespInfo.requestError(e.getCode(), e.getMsg());
		log.error(respInfo.toJSONString());
		this.returnMessage(request, response, respInfo);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 返回压缩响应或者非压缩响应
	 * @Date 9:58 2018/10/12
	 * @Param [request, response, respInfo]
	 * @Throws
	 * @return void
	 **/
	private void returnMessage(HttpServletRequest request, HttpServletResponse response,
                               RespInfo respInfo) {
		response.setCharacterEncoding("UTF-8");
		String requestUrl = request.getRequestURI();// 获取请求路径
		try {
			if (requestUrl.contains(GLOBALCONFIG.GZIP_URI1) || requestUrl.contains(GLOBALCONFIG.GZIP_URI2)
					|| requestUrl.contains(GLOBALCONFIG.GZIP_URI3)) {// 若指定路径，进行gzip压缩响应
				response = ((GzippedOutputStreamWrapper) response).getHttpServletResponse();
				GzippedOutputStreamWrapper.sendMessage(response, JSONObject.toJSON(respInfo).toString());
			} else {
				String contentType = "application/json;charset=UTF-8";
				response.setContentType(contentType);
				PrintWriter out = response.getWriter();
				out.print(JSONObject.toJSON(respInfo).toString());
				out.flush();
				out.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
		}
	}
	/**
	 * @Author qipengpai
	 * @Description //TODO 用户token异常
	 * @Date 10:02 2018/10/12
	 * @Param [response, ex]
	 * @Throws
	 * @return BaseResponse
	 **/
    @ExceptionHandler(UserTokenException.class)
    public RespInfo userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(),ex);
        return  RespInfo.requestError(ex.getCode(), ex.getMsg());
    }
}
