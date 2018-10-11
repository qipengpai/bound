package com.qpp.utils.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author qipengpai
 * @Title: ServiceBeanTool
 * @ProjectName bound
 * @Description: TODO Spring bean 工具类
 * @date 15:59 2018/10/11
 */
@Component
public final class ServiceBeanTool implements ApplicationContextAware {

    private static ApplicationContext wac;

    public static Object getBean(String beanId) {

        return  wac.getBean(beanId);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return  wac.getBean(beanClass);
    }

    public static <T> T getBean(String beanId, Class<T> beanClass) {
        return  wac.getBean(beanId, beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.wac = applicationContext;

    }
}
