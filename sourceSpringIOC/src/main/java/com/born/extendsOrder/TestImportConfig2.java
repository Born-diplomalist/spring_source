package com.born.extendsOrder;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:04:28
 */
@Configuration
@ComponentScan("com.born")
@Import(TestImportBeanDefinitionRegistrar.class)
public class TestImportConfig2 {


}