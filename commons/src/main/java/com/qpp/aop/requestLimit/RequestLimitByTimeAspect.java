/******************************************************************
 *
 *    Package:     com.company.platform.base.aop.RequestLimit
 *
 *    Filename:    RequestLimitByTimeAspect.java
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
package com.qpp.aop.requestLimit;


import com.qpp.baseenum.ResponseConstants;
import com.qpp.exception.BusinessException;
import com.qpp.model.basic.RequestLimitByTimeModel;
import com.qpp.service.requestLimit.IRequestLimitByTimeService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author qipengpai
 * @Title: RequestAccessAspect
 * @ProjectName bound
 * @Description: TODO (单位时间内请求次数限制AOP)
 * @date 9:34 2018/10/12
 */
@Component
@Aspect
@Slf4j
public class RequestLimitByTimeAspect {

	@Autowired
    IRequestLimitByTimeService requestLimitByTimeServiceImp;

	public RequestLimitByTimeAspect() {
		if (log.isInfoEnabled()) {
			log.info("实例RequestLimitByTimeAspect");
		}
	}

	@Pointcut("@annotation(com.qpp.aop.requestLimit.RequestLimitByTimeAnnotation)")
	public void controllerAspect() {
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 拦截controller且@requestLimitByTimeAnnotation
	 * @Date 11:50 2018/10/12
	 * @Param [jp, requestLimitByTimeAnnotation]
	 * @Throws
	 * @return void
	 **/
	@Before(value = "controllerAspect()&&@annotation(requestLimitByTimeAnnotation)")
	public void limitByTime(JoinPoint jp, RequestLimitByTimeAnnotation requestLimitByTimeAnnotation)
			throws BusinessException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI().toString();
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		final String key = "req_limit_".concat(userAgent.getBrowser().name()).concat(uri).concat(ip);
		if (requestLimitByTimeServiceImp.getRequestTimesByKey(key) == null
				|| requestLimitByTimeServiceImp.getRequestTimesByKey(key).getTimes() == 0) {// 初始添加一次访问
			RequestLimitByTimeModel requestLimitByTimeModel = new RequestLimitByTimeModel();
			requestLimitByTimeModel.setKey(key);
			requestLimitByTimeModel.setTimes(1);
			requestLimitByTimeServiceImp.putRequestTimesByKey(requestLimitByTimeModel, key);
		} else {// 增加一次访问
			RequestLimitByTimeModel requestLimitByTimeModel = new RequestLimitByTimeModel();
			requestLimitByTimeModel.setKey(key);
			requestLimitByTimeModel.setTimes(requestLimitByTimeServiceImp.getRequestTimesByKey(key).getTimes() + 1);
			requestLimitByTimeServiceImp.putRequestTimesByKey(requestLimitByTimeModel, key);
		}

		int count = requestLimitByTimeServiceImp.getRequestTimesByKey(key).getTimes(); // 获取总访问次数
		if (count >= requestLimitByTimeAnnotation.count()) {// 当请求数大于等于限制次数
			Timer timer = new Timer();
			TimerTask task = new TimerTask() { // 创建一个新的计时器任务。

				@Override
				public void run() {
					requestLimitByTimeServiceImp.delRequestTimesByKey(key);
				}
			};
			timer.schedule(task, requestLimitByTimeAnnotation.time());
			// 安排在指定延迟后执行指定的任务。task : 所要安排的任务。 执行任务前的延迟时间，单位是毫秒。
		}

		if (count > requestLimitByTimeAnnotation.count()) { // 大于约定时间内执行次数，抛出异常
			log.info("用户IP[" + ip + "]访问地址[" + uri + "]超过了限定的次数[" + requestLimitByTimeAnnotation.count() + "]");
			throw new BusinessException(ResponseConstants.REQUEST_LIMIT.getRetCode(),
					"超过规定" + requestLimitByTimeAnnotation.time() / 1000 + "秒时间内访问"
							+ requestLimitByTimeAnnotation.count() + "次限制，请稍后再试");
		}
	}
}
