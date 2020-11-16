package com.born.test;

import org.spring.util.AnnotationConfigApplicationContext;

/**
 * @Description: 自定义AnnotationConfigApplicationContext
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 16:43:10
 */
public class DIYAnnotationConfigACTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.born.service");
	}
}
