package com.born.Import;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MyInvocationHandler2 implements InvocationHandler {

	Object target;

	public MyInvocationHandler2(Object target){
		this.target=target;
	}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我正在代理对象 MyInvocationHandler2");
		return method.invoke(target,args);
    }
}
