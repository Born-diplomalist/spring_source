package com.born.other;

import org.springframework.beans.factory.annotation.Required;

/**
 * @Description: 测试RequiredAnnotationBeanPostProcessor
 * 该后置处理器在AbstractAppliCationContext的registerBeanPostProcessors中被添加
 * 作用是解析@Required
 * 希望测试其作用，可以在源码中，从后置处理器列表中remove掉该处理器，此时即使@Required修改的方法不符合条件也不会报错，@Required等于不存在
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-27 08:18:58
 */
public class TestRequired {

	String name;

	public String getName() {
		return name;
	}

	//加了该注解之后，在实例化时必须为该方法传入一个String参数，否则报错
	@Required
	public void setName(String name) {
		this.name = name;
	}
}
