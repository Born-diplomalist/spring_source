package com.born.Import;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MyInvocationHandler implements InvocationHandler {

    /**
     *
     * @param proxy 代理对象
     * @param method 目标对象
     * @param args    目标方法的参数
     * @return
     * @throws Throwable
     *
     *
     * proxy logic execute
     * target logic execute
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("MyInvocationHandler proxy");
		return method.invoke(args);
    }
}
