package com.born.cglib;

import com.born.dao.TestConfigurationAnnotation1;
import com.born.dao.TestConfigurationAnnotation2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: 没有加@Configuration注解，属于部分配置类
 * testConfigurationAnnotation2调用testConfigurationAnnotation1  testConfigurationAnnotation1构造方法中的语句打印2次
 *
 * 没有进行代理，1初始化时调用一次1的构造，2初始化时又调用了一次1的构造，两次
 * 获取AppConfig_LITE的对象，得到的是非代理对象
 *
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 15:12:15
 */

@ComponentScan("com.born")
public class AppConfig_LITE {


	@Bean
	public TestConfigurationAnnotation1 testConfigurationAnnotation1(){
		return new TestConfigurationAnnotation1();
	}

	@Bean
	public TestConfigurationAnnotation2 testConfigurationAnnotation2(){
		testConfigurationAnnotation1();
		return new TestConfigurationAnnotation2();
	}


}
