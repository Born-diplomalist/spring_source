package com.born.extendsOrder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:05:48
 */
@Component
public class TestInitializingBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("TestInitializingBean---afterPropertiesSet");
	}
}
