package com.qpp.service.message.imp;


import com.qpp.service.message.IMessageProvider;
import com.qpp.service.message.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qipengpai
 * @Title: EucpMassageProvider
 * @ProjectName bound
 * @Description: TODO 亿美短信
 * @date 9:44 2018/10/12
 */
@Service
public class EucpMassageProvider implements IMessageProvider {

    @Autowired
    ISender eucpSMSSender;

    @Override
    public ISender produce() {
        return eucpSMSSender;
    }

    @Override
    public ISender produce(Class<? extends ISender> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ISender) Class.forName(clazz.getName()).newInstance();
    }
}
