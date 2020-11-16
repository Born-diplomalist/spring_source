package com.born.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义BeanFactoryPostProcessor  在Bean工厂实例化后做扩展
 * 也就是在容器准备好之后，修改容器里的内容
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-02 17:55:44
 */
//@Component
public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		//从BeanFactory的实现中拿出指定的BeanDefinition并修改其scope属性为prototype
		AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanFactory.getBeanDefinition("indexDao");
		annotatedBeanDefinition.setScope("prototype");
	}
}
