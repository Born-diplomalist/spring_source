package com.born.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义BeanPostProcessor，实现在Bean初始化前后进行操作
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-02 11:28:02
 *
 * PriorityOrdered 实现该接口的方法可以对多个后置处理器进行排序
 */
@Component
public class TestBeanPostProcessor2 implements BeanPostProcessor, PriorityOrdered {


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("singleDaoWithPrototype")){
			System.out.println("singleDaoWithPrototype--BeforeInitialization2");
		}
		//返回的类型是Object类型，因此此处可以将这个bean进行代理并返回代理后的bean
		//Object proxyBean = Proxy.newProxyInstance();
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("singleDaoWithPrototype")){
			System.out.println("singleDaoWithPrototype--AfterInitialization2");
		}
		return bean;
	}

	/**
	 * 值小的先执行
	 * @return
	 */
	@Override
	public int getOrder() {
		return 100;
	}
}
