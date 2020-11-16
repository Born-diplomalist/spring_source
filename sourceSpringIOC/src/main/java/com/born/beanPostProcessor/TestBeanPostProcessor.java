package com.born.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * Bean的后置处理器  作用是在Bean实例化之后插入我们的逻辑
 *
 * @Description: 自定义后置处理器，需要加上@Component注解 可定义多个，依次执行
 * 如果是Spring自己提供的后置处理器，默认不会加@Component，Spring会去手动地将这些处理器添加进去
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-02 11:28:02
 *
 * PriorityOrdered 实现该接口的方法可以对多个后置处理器进行排序，数值小的先执行
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor, PriorityOrdered {


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("singleDaoWithPrototype")){
			System.out.println("TestBeanPostProcessor--singleDaoWithPrototype--BeforeInitialization");
		}
		//返回的类型是Object类型，因此此处可以将这个bean进行代理并返回代理后的bean
		//Object proxyBean = Proxy.newProxyInstance();
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("singleDaoWithPrototype")){
			System.out.println("TestBeanPostProcessor--singleDaoWithPrototype--AfterInitialization");
		}
		return bean;
	}

	/**
	 * 值小的先执行
	 * @return
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}
