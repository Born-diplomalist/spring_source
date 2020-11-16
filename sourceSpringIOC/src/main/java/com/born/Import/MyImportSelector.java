package com.born.Import;

import com.born.beanPostProcessor.TestBeanPostProcessor3;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description: 自定义ImportSelector，用于向Spring容器中加入指定的beanDefinition
 * 弊端1：需要先知道类名，因此如果希望将动态生成的类放入Spring容器，该方式不适用（比如Mapper接口的实现类）
 * 弊端2：作用和@Component差不多，使用起来不如@Component简单
 *
 * 用途1：可以在该类中定义指定的几个类，然后通过添加或删除@Import(MyImportSelector.class)的形式，实现某几个类的动态加载（类似一个开关）
 * 如果将其进一步封装，将这个@Import(MyImportSelector.class)加到一个新注解上，起名字为@EnableXXX，就实现了对功能动态加载卸载的效果，参考SpringBoot或者AOP或者SpringCloud的很多@EnableXxx
 * 用途2：如果一些类存在的位置比较分散，不希望为了这一两个类而在@ComponentScan中将扫描包的范围扩充很大，可以使用该类将这些分散的类单独添加进去。
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-04 10:36:11
 */
public class MyImportSelector implements ImportSelector {

	/**
	 * 返回值中的类名数组中的每一个类名，都会被转换为BeanDefinition加载到Spring容器中,无需加@Component注解
	 * @param importingClassMetadata
	 * @return
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//TestBeanPostProcessor3处理器被放到容器中，会导致对indexDao的获取Bean请求
		return new String[]{TestBeanPostProcessor3.class.getName()};
	}
}
