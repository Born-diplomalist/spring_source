package com.born.cglib;

import com.born.dao.TestConfigurationAnnotation1;
import com.born.dao.TestConfigurationAnnotation2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 加了@Configuration注解，属于全配置类
 * testConfigurationAnnotation2调用testConfigurationAnnotation1  testConfigurationAnnotation1构造方法中的语句打印1次
 * 分析：当testConfigurationAnnotation1的方法被调用的时候，调用的已经不是原来的方法了，因为按原来的方法必然会再次创建TestConfigurationAnnotation1对象，再次打印。第一次能打印是因为代理前目标对象的初始化
 * 对方法进行更改：重载和重写都不现实，实际上是当前配置类进行了CGLIB代理，之后会通过BeanFactory的getBean获取对象而不再是去new一个对象，第二次调用的是代理对象的方法
 * 验证：在AnnotationConfigApplicationContext构造函数中加载当前配置类，getBean获取AppConfig_FULL获取的是代理对象
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 15:12:15
 */
@Configuration
@ComponentScan("com.born")
public class AppConfig_FULL {

	//没有被static修饰，是通过FactoryBean的getObjects获取，只创建一次对象
	@Bean
	public TestConfigurationAnnotation1 testConfigurationAnnotation1(){
		return new TestConfigurationAnnotation1();
	}

	//如果方法被static修饰，那么不再是通过FactoryBean的getObjects获取，会创建两次对象
	//@Bean
	//public static TestConfigurationAnnotation1 testConfigurationAnnotation1(){
	//	return new TestConfigurationAnnotation1();
	//}

	@Bean
	public TestConfigurationAnnotation2 testConfigurationAnnotation2(){
		testConfigurationAnnotation1();
		return new TestConfigurationAnnotation2();
	}

}
