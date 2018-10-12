package com.qpp.service.message;

/** 
* @ClassName: IMessageProvider 
* @Description: TODO(消息推送) 
* @author zhengjn 
* @date 2017年10月20日 下午4:07:16 
*  
*/

/**
 * @author qipengpai
 * @Title: IMessageProvider
 * @ProjectName bound
 * @Description: TODO 消息推送
 * @date 9:44 2018/10/12
 */
public interface IMessageProvider {

    ISender produce();

    public ISender produce(Class<? extends ISender> clazz)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
