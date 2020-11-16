package com.born.Import;

import com.born.dao.CardDao;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
//import org.apache.ibatis.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 定制的FactoryBean，当获取CardDao接口类型对象时，getObject()会返回代理对象
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-04 10:59:14
 */
public class MyFactoryBean implements FactoryBean, InvocationHandler {

	Class clazz;

	public MyFactoryBean(Class clazz){
		this.clazz=clazz;
	}


	@Override
	public Object getObject() throws Exception {
		Class[] clazzArray = new Class[]{clazz};
		Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), clazzArray,this);
		return proxy;
	}

	@Override
	public Class<?> getObjectType() {
		return clazz;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy");
		Method method1=proxy.getClass().getInterfaces()[0].getMethod(method.getName(),String.class);
		//Select select = method1.getDeclaredAnnotation(Select.class);
		//System.out.println(select.value());
		return null;
	}
}
