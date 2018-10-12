/******************************************************************
 *
 *    Package:     com.company.platform.dao.requestAccess
 *
 *    Filename:    RequestAccessMapper.java
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
package com.qpp.dao.requestAccess;

import java.util.Map;

/**
 * @author qipengpai
 * @Title: LoginAssistMapper
 * @ProjectName bound
 * @Description: TODO 增加请求访问记录
 * @date 13:44 2018/10/11
 */
public interface RequestAccessMapper {

	/**
	 * @Author qipengpai
	 * @Description //TODO 添加用户访问信息
	 * @Date 11:40 2018/10/12
	 * @Param [info]
	 * @Throws
	 * @return void
	 **/
	void addRequestAccessInfo(Map<String, Object> info);

}
