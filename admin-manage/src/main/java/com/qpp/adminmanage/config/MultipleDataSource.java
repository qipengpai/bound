package com.qpp.adminmanage.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @项目名称：wyait-manage
 * @类名称：MultipleDataSource
 * @类描述：创建多数据源注册到Spring中
		     接口：BeanDefinitionRegistryPostProcessor只要是注入bean,
		     接口：接口 EnvironmentAware 重写方法 setEnvironment
		     可以在工程启动时，获取到系统环境变量和application配置文件中的变量。 
		     方法的执行顺序是：
		  setEnvironment()-->postProcessBeanDefinitionRegistry() --> postProcessBeanFactory()
 * @创建人：wyait
 * @创建时间：2017年12月19日 下午2:49:34 
 * @version：
 */
@Configuration
public class MultipleDataSource {
    //作用域对象.
    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    //bean名称生成器.
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    /**
     * 注册Bean到Spring
     */
    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        // 可以自动生成name
        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));
        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }
}
