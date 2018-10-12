/******************************************************************
 *
 *    Package:     com.company.platform.base.baseenum
 *
 *    Filename:    GLOBALCONFIG.java
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
package com.qpp.baseenum;


/**
 * @author qipengpai
 * @Title: GLOBALCONFIG
 * @ProjectName bound
 * @Description: TODO 系统常数
 * @date 9:34 2018/10/12
 */
public interface GLOBALCONFIG {

	/**
	 * @Fields LOCKTIMES : TODO(被锁定限定次数)
	 */
	String LOCKTIMES = "LOCKTIMES";

	/**
	 * @Fields APPKEY : TODO(用一句话描述这个变量表示什么)
	 */
	String APPKEY = "12345678";

	/**
	 * @Fields APPKEY : TODO(新注册用户默认角色)
	 */
	String DEFAULT_ROLE = "COMMON_ROLE";

	/**
	 * @Fields APPKEY : TODO(蚂蚁金盾app 默认角色id)
	 */
	String DEFAULT_MANAGER_ROLE = "2";

	/** 
	* @Fields GZIP_URI : TODO(指定gzip压缩过滤器的URI) 
	*/
	String GZIP_URI_FILTER1 = "/appApi/*";

	/** 
	* @Fields GZIP_URI : TODO(用一句话描述这个变量表示什么) 
	*/
	String GZIP_URI1 = "/appApi/";
	/** 
	 * @Fields GZIP_URI : TODO(指定gzip压缩过滤器的URI) 
	 */
	String GZIP_URI_FILTER2 = "/appCommonApi/*";

	/** 
	* @Fields GZIP_URI : TODO(用一句话描述这个变量表示什么) 
	*/
	String GZIP_URI2 = "/appCommonApi/";
	/** 
	 * @Fields GZIP_URI : TODO(指定gzip压缩过滤器的URI) 
	 */
	String GZIP_URI_FILTER3 = "/openCommonApi/*";

	/** 
	* @Fields GZIP_URI : TODO(用一句话描述这个变量表示什么) 
	*/
	String GZIP_URI3 = "/openCommonApi/";
}
