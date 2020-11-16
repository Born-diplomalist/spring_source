package com.born.config;

import com.born.Import.MyImportSelector;
import com.born.anno.EnableEureka;
import com.born.anno.MyScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 15:12:15
 */

@Configuration
@ComponentScan("com.born")
//@MyScan
//@EnableEureka
@EnableAspectJAutoProxy
@Import(value = MyImportSelector.class)
public class AppConfig {

	//@Bean
	//@Autowired

}
