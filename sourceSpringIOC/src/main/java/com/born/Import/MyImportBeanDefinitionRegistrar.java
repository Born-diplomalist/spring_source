package com.born.Import;

import com.born.dao.CardDao;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义ImportBeanDefinitionRegistrar，该接口可以将指定的类转换为BeanDefinition添加到容器中，
 * 并且与registry()和scan()不同的是，我们可以插手这个转换的过程（前两者不行）
 *
 *
 * 类转换为BeanDefinition的三种方式 registry()、scan()、ImportBeanDefinitionRegistrar
 *
 * 自动注入方式  byName  byType  byConstructor
 *
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-04 10:41:41
 */
@Component
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		//目标：新建一个BeanDefinition，类型是接口类型，getBean时可以根据接口类型名得到这个接口的代理对象，实际上就是模拟Mybatis

		/*
		 	方式A。 创建好代理对象，使用代理对象类型构建BeanDefinition，将代理对象放到容器中，希望用接口名获取到代理对象。
		 但是代理对象的类名是生成的，通过beanName获取BeanDefinition时会报错，该方式失败
		 */
		//CardDao proxyDao = (CardDao) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{CardDao.class}, new MyInvocationHandler(CardDao.class));
		//BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(proxyDao.getClass());
		//GenericBeanDefinition genericBeanDefinition= (GenericBeanDefinition) builder.getBeanDefinition();
		//registry.registerBeanDefinition("cardDao",genericBeanDefinition);


		/*
			方式B。借助于FactoryBean，首先用接口类型构建BeanDefinition，然后对这个BeanDefinition的BeanClass设置为自定义的FactoryBean
		这个FactoryBean的构造函数会接收一个Class类型，然后它的getObject返回的是这个类型的代理对象，它的getObjectType是这个类型
		这个BeanDefinition我们传的是接口类型，因此就实现按照接口类型可以查找到该BeanDefinition，而且获取对象时获取到的是代理类型了
		 */
		//从Spring容器中拿出了CardDao.class类型的那个bean，这是一个接口类型，默认无法实例化，会报错
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CardDao.class);
		GenericBeanDefinition beanDefinition= (GenericBeanDefinition) builder.getBeanDefinition();
		//由于使用了MyFactoryBean带参数的构造方法而非默认构造方法，而Spring默认是找空参构造来实例化的，因此会报无法实例化该类的错误
		//可以为这个BeanDefinition添加一个构造器参数，便于之后按构造器参数自动注入
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.born.dao.CardDao");
		//将MyFactoryBean对象放到容器中，这样在从容器中按照CardDao类型进行匹配的时候，可以匹配到该对象，且该对象会返回代理对象
		beanDefinition.setBeanClass(MyFactoryBean.class);
		registry.registerBeanDefinition("cardDao",beanDefinition);
	}
}
