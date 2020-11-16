package com.born.dao;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @Description: 一个单例Bean，依赖了原型Bean
 *  如何让这个原型Bean有多次实例化的机会？ ApplicationContextAware
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-02 11:28:50
 */
@Repository
public class SingleDaoWithPrototype implements ApplicationContextAware,Dao{

	private ApplicationContext applicationContext;

	//原型的bean
	private PrototypeDao prototypeDao;


	public SingleDaoWithPrototype(){
		System.out.println("SingleDao的构造方法执行了~");
	}


	@PostConstruct
	public void init(){
		System.out.println("bean实例化后的回调--初始化--init");
	}

	public void query(){
		System.out.println("IndexDao的query方法执行了~");
		//处理单例的Bean依赖了一个原型的Bean,这个原型Bean被外围的单例Bean缓存起来了，没有第二次实例化机会的问题
		applicationContext.getBean("prototypeDao");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware---setApplicationContext");
		this.applicationContext=applicationContext;
	}
}
