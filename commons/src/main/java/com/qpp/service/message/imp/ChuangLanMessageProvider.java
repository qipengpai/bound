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

/***
 * 
* @ClassName: ChuangLanMessageProvider 
* @Description: TODO(创蓝短信发送) 
* @author lo 
* @date 2018年5月25日 下午2:20:01 
*
 */

@Service
public class ChuangLanMessageProvider implements IMessageProvider {

    @Autowired
    ISender chuangLanSMSSender;

    /*
     * (非 Javadoc) <p>Title: produce</p> <p>Description: </p>
     * @return
     * @see com.company.platform.base.service.message.IMessageProvider#produce()
     */
    @Override
    public ISender produce() {
        // TODO Auto-generated method stub
        return chuangLanSMSSender;
    }

    /** 
     * @Title: produce 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
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
