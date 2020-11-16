package com.born.extendsOrder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:00:27
 */
public class TestBeanPostProcessor implements BeanPostProcessor {

	//在Bean实例化之后，放入容器之前调用
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("TestBeanPostProcessor--before");
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("TestBeanPostProcessor--after");
		return null;
	}
}
