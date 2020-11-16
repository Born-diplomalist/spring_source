package com.born.extendsOrder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:03:17
 */
@Component
public class TestBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("TestBeanDefinitionRegistryPostProcessor---postProcessBeanDefinitionRegistry");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("TestBeanDefinitionRegistryPostProcessor---postProcessBeanFactory");
	}
}
