package com.born.extendsOrder;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:05:10
 */
public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("TestImportBeanDefinitionRegistrar---registerBeanDefinitions");
	}
}
