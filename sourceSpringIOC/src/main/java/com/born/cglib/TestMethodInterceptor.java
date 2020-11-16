package com.born.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: 测试ConfigurationClassEnhancer中setCallbackFilter的作用
 * CallbackFilter有三个，第一个是BeanMethodInterceptor
 * 方法拦截器
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-18 10:06:10
 */
public class TestMethodInterceptor implements MethodInterceptor {

	/**
	 *
	 * @param o
	 * @param method  正在执行的方法
	 * @param objects
	 * @param methodProxy 调用的方法
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("method-interceptor");
		return methodProxy.invokeSuper(o,objects);
	}
}
