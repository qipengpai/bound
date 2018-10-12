/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    SMSSender.java
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
package com.qpp.service.message.imp;

import com.qpp.service.message.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


/**
 * @author qipengpai
 * @Title: IMeaageService
 * @ProjectName bound
 * @Description: TODO 短信发送
 * @date 9:34 2018/10/12
 */
@Slf4j
@Service
public class WeimiSMSSender implements ISender {
    

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * @Fields url : TODO(微米短信服务地址)
	 */
	private String url = "http://api.weimi.cc/2/sms/send.html";

	/**
	 * @Fields uid : TODO(微米账号接口UID)
	 */
	private String uid = "YpjjErfy3E5g";

	/**
	 * @Fields pas : TODO(微米账号接口密码)
	 */
	private String pas = "jub8dvsz";

	/**
	 * @Fields type : TODO(接口返回类型：json、xml、txt。默认值为txt)
	 */
	private String type = "json";

	@Override
	@Async
	public void send(Map<String, Object> para) {
		if (log.isInfoEnabled()) {
			log.info("微米短信发送开始");
		}
		para.put("uid", uid);
		para.put("pas", pas);
		para.put("type", type);
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE.toString());
		if (para != null) {
			HttpEntity<String> entity = new HttpEntity<String>(this.constructURLString(para), headers);
			ResponseEntity<String> resp = restTemplate.postForEntity(url, entity, String.class);
			if (resp.getStatusCode().value() == HttpStatus.OK.value()) {
				log.info(resp.getBody());
			}
		}
		if (log.isInfoEnabled()) {
			log.info("微米短信发送结束");
		}
	}

	/** 
	* @Title: constructURLString 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param parameters
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String constructURLString(Map<String, Object> parameters) {
		StringBuffer url = new StringBuffer();

		boolean first = true;

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			try {
				// type checking, we can get nulls in here
				if ((entry.getValue() == null) || (entry.getKey() == null)) {
					continue;
				}
				if (entry.getValue().toString().length() == 0) {
					continue;
				}

				if (first) {
					first = false;
				} else {
					url.append("&");
				}
				url.append(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
						+ URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
			} catch (UnsupportedEncodingException ex) {
				ex.printStackTrace();
				// No Fixing this, really
				throw new Error("Unsupported Encoding Exception", ex);
			}
		}

		return url.toString();
	}

    @Override
    public void sendAll(Map<String, Object> para) {
        // TODO Auto-generated method stub
        
    }
}
