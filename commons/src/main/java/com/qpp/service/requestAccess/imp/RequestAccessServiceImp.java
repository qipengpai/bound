/******************************************************************
 *
 *    Package:     com.company.platform.service.requestAccess.imp
 *
 *    Filename:    RequestAccessServiceImp.java
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
package com.qpp.service.requestAccess.imp;


import com.qpp.dao.requestAccess.RequestAccessMapper;
import com.qpp.service.requestAccess.IRequestAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * @author qipengpai
 * @Title: ResponseConstantsErrorMessage
 * @ProjectName bound
 * @Description: TODO 增加请求访问记录
 * @date 13:44 2018/10/11
 */
@Service
public class RequestAccessServiceImp implements IRequestAccessService {

	@Autowired
	private RequestAccessMapper requestAccessMapper;

    /**
     * @Author qipengpai
     * @Description //TODO 添加用户操作日志
     * @Date 11:36 2018/10/12
     * @Param [info]
     * @Throws
     * @return void
     **/
	@Override
	@Transactional
	@Async
	public void addRequestAccessInfo(Map<String, Object> info) {
		// TODO Auto-generated method stub
		requestAccessMapper.addRequestAccessInfo(info);
	}

}
