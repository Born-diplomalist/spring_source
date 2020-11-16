package com.born.dao;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-07 07:15:32
 */
public class TestConfigurationAnnotation1 implements Dao{

	@Override
	public void query() {
		System.out.println("TestConfigurationAnnotation1");
	}
}
