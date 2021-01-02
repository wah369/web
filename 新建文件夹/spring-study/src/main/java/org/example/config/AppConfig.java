package org.example.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-18
 * Time: 11:58
 */
@Configuration
public class AppConfig implements BeanPostProcessor {
    @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
    public LifeCycleTest lifeCycleTest(){
        return new LifeCycleTest();
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String
            beanName) throws BeansException {
        System.out.println("BeanPostProcessor Before");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String
            beanName) throws BeansException {
        System.out.println("BeanPostProcessor After");
        return bean;
    }
}