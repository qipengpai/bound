/******************************************************************
 *
 *    Package:     com.company.platform.aop.requestAccess
 *
 *    Filename:    RequestAccessAnnotation.java
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
package com.qpp.aop.requestAccess;

import com.qpp.baseenum.RequestAccessConstants;

import java.lang.annotation.*;

/**
 * @author qipengpai
 * @Title: RequestAccessAnnotation
 * @ProjectName bound
 * @Description: TODO 增加请求访问记录注解
 * @date 15:02 2018/10/11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
public @interface RequestAccessAnnotation {
	/**
	 * @Field @methodName : TODO(这注解模块编码)
	 */
	String modelName() default "无操作模块名称";

	/**
	 * @Field @methodName : TODO(这注解模块编码)
	 */
	RequestAccessConstants modelType();

	/**
	 * @Description (注解操作描述)
	 * @return
	 */
	public String desc() default "无操作描述";
}
