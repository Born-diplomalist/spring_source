package com.born.beanPostProcessor;

import com.born.Import.MyInvocationHandler2;
import com.born.dao.Dao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-04 17:02:13
 */
public class TestBeanPostProcessor3 implements BeanPostProcessor {

	/**
	 *
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return 返回的对象会真正放到Spring容器中
	 * @throws BeansException
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("indexDao")){
			bean=Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Dao.class},new MyInvocationHandler2(bean));
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanpostprocessor3-----------");
		return bean;
	}
}
