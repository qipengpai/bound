/******************************************************************
 *
 *    Package:     com.company.platform.base.detailedfilter
 *
 *    Filename:    SimpleCORSFilter.java
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
package com.qpp.config.filter.detailedfilter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author qipengpai
 * @Title: SimpleCORSFilter
 * @ProjectName bound
 * @Description: TODO 跨域处理器过滤器
 * @date 9:34 2018/10/12
 */
public class SimpleCORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		final String origin = req.getHeader("origin");
		if (StringUtils.isNotEmpty(origin)) {
			res.setHeader("Access-Control-Allow-Origin", origin);
		} else {
			res.setHeader("Access-Control-Allow-Origin", "*");
		}
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		chain.doFilter(request, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
