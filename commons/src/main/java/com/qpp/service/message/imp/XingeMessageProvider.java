/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message.imp
 *
 *    Filename:    XingeMessageProvider.java
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


import com.qpp.service.message.IMessageProvider;
import com.qpp.service.message.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author qipengpai
 * @Title: RespInfo
 * @ProjectName bound
 * @Description: TODO 发信鸽推送
 * @date 9:44 2018/10/12
 */
@Service
public class XingeMessageProvider implements IMessageProvider {

    @Autowired
    ISender xingePushSender;

    /*
     * (非 Javadoc) <p>Title: produce</p> <p>Description: </p>
     * @return
     * @see com.company.platform.base.service.message.IMessageProvider#produce()
     */
    @Override
    public ISender produce() {
        // TODO Auto-generated method stub
        return xingePushSender;
    }

    /**
     * @Author qipengpai
     * @Title: produce
     * @Date 18:07 2018/10/12
     * @Description: TODO 定制开发短信平台实现，通过反射实现，需实现ISender接口类
     * @param @param clazz
     * @param @return
     * @param @throws InstantiationException
     * @param @throws IllegalAccessException
     * @param @throws ClassNotFoundException    设定文件 
     * @return ISender    返回类型 
     * @throws 
     */
    public ISender produce(Class<? extends ISender> clazz)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ISender) Class.forName(clazz.getName()).newInstance();
    }
}
