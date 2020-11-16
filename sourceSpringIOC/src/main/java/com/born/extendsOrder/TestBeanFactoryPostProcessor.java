package com.born.extendsOrder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:01:57
 */
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	//在BeanFactory实例化之后调用，可以在任意一个bean实例化之前做回调
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("TestBeanFactoryPostProcessor");
	}
}
