/******************************************************************
 *
 *    Package:     com.company.platform.model
 *
 *    Filename:    BaseHttpParamsResp.java
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
package com.qpp.model.result;

import com.alibaba.fastjson.JSON;
import com.qpp.baseenum.ResponseConstants;
import lombok.Data;


import java.io.Serializable;


/**
 * @author qipengpai
 * @Title: RespInfo
 * @ProjectName bound
 * @Description: TODO 非分页返回类型
 * @date 9:44 2018/10/12
 */
@Data
@SuppressWarnings("serial")
public class RespInfo implements Serializable {

    /**
     * 标识是否成功调用 true 成功 false 失败
     */
    private String exceptionFlag;
    /**
     * @Fields responseCode : TODO(响应状态码)
     */
    private String responseCode;

    /**
     * @Fields responseMessage : TODO(响应码描述)
     */
    private String responseMessage;

    /**
     * @Fields beans : TODO(自定义结果集)
     */
    // 结果集
    private Object resultSet;

    public RespInfo() {
    }

    //成功返回
    public RespInfo(Object resultSet, String responseMessage) {
        this.resultSet=resultSet;
        this.exceptionFlag = ResponseConstants.REQUEST_SUCCESS.getRetCode();
        this.responseCode = ResponseConstants.SUCCESS.getRetCode();
        this.responseMessage = responseMessage;
    }
    //自定义返回
    public RespInfo(Object resultSet, String exceptionFlag, String responseCode, String responseMessage) {
        this.resultSet=resultSet;
        this.exceptionFlag = exceptionFlag;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public RespInfo(String exceptionFlag, String responseMessage) {
        this.exceptionFlag = exceptionFlag;
        this.responseMessage = responseMessage;
    }

    public RespInfo(String exceptionFlag, String responseCode, String responseMessage) {
        this.exceptionFlag = exceptionFlag;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public RespInfo(String exceptionFlag, String responseCode, String responseMessage, Object resultSet) {
        this.exceptionFlag = exceptionFlag;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.resultSet = resultSet;
    }

    // 请求失败
    public static RespInfo requestError(String responseMessage) {
        return new RespInfo(ResponseConstants.REQUEST_FAIL.getRetCode(), responseMessage);
    }

    // 请求成功，验证报文异常
    public static RespInfo requestError(String responseCode, String responseMessage) {
        return new RespInfo(ResponseConstants.REQUEST_SUCCESS.getRetCode(), responseCode, responseMessage);
    }

    // 成功调用，并放回信息
    public static RespInfo requestSuccess(String responseMessage, Object resultSet) {
        return new RespInfo(ResponseConstants.REQUEST_SUCCESS.getRetCode(),
                ResponseConstants.SUCCESS.getRetCode(), responseMessage, resultSet);
    }

    // 成功调用，并放回信息
    public static RespInfo requestSuccess(String responseCode, String responseMessage, Object resultSet) {
        return new RespInfo(ResponseConstants.REQUEST_SUCCESS.getRetCode(), responseCode, responseMessage,
                resultSet);
    }


    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
