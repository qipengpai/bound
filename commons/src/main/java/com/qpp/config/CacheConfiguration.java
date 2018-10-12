package com.qpp.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * @author qipengpai
 * @Title: ResponseConstants
 * @ProjectName bound
 * @Description: TODO ehcache缓存实用类
 * @date 9:34 2018/10/12
 */
@Configuration
@EnableCaching// 标注启动了缓存
public class CacheConfiguration {
    /**
     *    ehcache 主要的管理器
     *
     * @author pengpai
     * @date 2018/3/10 15:50
     * @param bean
     * @return org.springframework.cache.ehcache.EhCacheCacheManager
     */
    @Bean(name = "appEhCacheCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject ());
    }

    /**
     *    据shared与否的设置
     *    Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     * @author pengpai
     * @date 2018/3/10 15:51
     * @param
     * @return org.springframework.cache.ehcache.EhCacheManagerFactoryBean
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("config/ehcache.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }
}
