package com.born.test;


import com.born.beanFactoryPostProcessor.MyBeanFactoryProcessor;
import com.born.config.AppConfig;
import com.born.dao.*;
import com.born.extendsOrder.TestBeanDefinitionRegistryPostProcessor;
import com.born.extendsOrder.TestBeanFactoryPostProcessor;
import com.born.extendsOrder.TestImportConfig;
import com.born.extendsOrder.TestImportConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 08:42:14
 */
public class Test {

	public static void main(String[] args) {

		//07.测试7个扩展点的打印顺序
		AnnotationConfigApplicationContext annotationConfigApplicationContext5=new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext5.addBeanFactoryPostProcessor(new TestBeanDefinitionRegistryPostProcessor());
		annotationConfigApplicationContext5.addBeanFactoryPostProcessor(new TestBeanFactoryPostProcessor());
		annotationConfigApplicationContext5.register(TestImportConfig2.class);
		annotationConfigApplicationContext5.register(TestImportConfig.class);
		annotationConfigApplicationContext5.refresh();


		//06.一个类加了@Configuration时会被cglib代理，否则就是普通类  FULL和LITE的区别
		//AnnotationConfigApplicationContext annotationConfigApplicationContext4=new AnnotationConfigApplicationContext(AppConfig.class);
		//AppConfig appConfigFull = (AppConfig) annotationConfigApplicationContext4.getBean("appConfig_FULL");//代理对象
		//AppConfig appConfigLite = (AppConfig) annotationConfigApplicationContext4.getBean("appConfig_LITE");//原对象


		//05.通过ImportSelector动态加载指定的类并将ImportSelector配置到@Import中，这个类是一个后置处理器，这个后置处理器的作用是，当bean类型为indexDao时，把代理对象放到bean容器中。这样当使用indexDao获取Bean时，返回的是代理对象
		//同时使用@EnableXxx对@Import(ImportSelector)进行封装，最终效果是，通过加@EnableXxx注解，让后置处理器生效，indexDao被代理；不加该注解，处理器不生效，indexDao未被代理，实现一个开关的效果。
		//AnnotationConfigApplicationContext annotationConfigApplicationContext3=new AnnotationConfigApplicationContext(AppConfig.class);
		//不能用IndexDao类型接收，因为返回的是代理对象
		//Dao indexDao = (Dao) annotationConfigApplicationContext3.getBean("singleDaoWithPrototype");
		//indexDao.query();

		//04.模拟Mybatis的@MapperScan，通过接口类型获取代理对象
		//AnnotationConfigApplicationContext annotationConfigApplicationContext2=new AnnotationConfigApplicationContext(AppConfig.class);
		//CardDao cardDao= (CardDao) annotationConfigApplicationContext2.getBean("cardDao");
		//cardDao.query();

		//03.以AnnotationConfigApplicationContext为例探究Spring初始化过程
		//AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext();
		//annotationConfigApplicationContext.register(AppConfig.class);
		//添加一个自定义的BeanFactoryPostProcessor，该类不要交给Spring管理
		//然后PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List<org.springframework.beans.factory.config.BeanFactoryPostProcessor>)会获取到该后置处理器
		//annotationConfigApplicationContext.addBeanFactoryPostProcessor(new MyBeanFactoryProcessor());

		//初始化spring的环境
		//annotationConfigApplicationContext.refresh();

		//getBean中执行了一些与refresh()内部相似的调用，因此如果仅注入单个bean，无需refresh()...
		//PrototypeDao prototype = annotationConfigApplicationContext.getBean(PrototypeDao.class);
		//prototype.input();


		//02.AnnotationConfigApplicationContext初始化时加入我们的特定逻辑，以及手动扫描bean
		//02.1 直接完成初始化
		//AnnotationConfigApplicationContext annotationConfigApplicationContextAll=new AnnotationConfigApplicationContext(AppConfig.class);
		//02.2 使用空参构造部分初始化，然后在register调用之前加入我们预期的特定逻辑，再完成剩下的初始化
		//AnnotationConfigApplicationContext annotationConfigApplicationContextPart=new AnnotationConfigApplicationContext();
		//annotationConfigApplicationContextPart.register(PrototypeDao.class);//在@ComponentScan未生效时，手动注入单个对象
		//annotationConfigApplicationContextPart.getEnvironment().setActiveProfiles("XXX");//@Profile的使用,需在register之前
		//annotationConfigApplicationContextPart.register(AppConfig.class);//注入配置类对象，搭配前面的空参构造和后面的refresh()一起完成初始化
		//annotationConfigApplicationContextPart.scan("com");//手动扫描包，扫描的过程可看到索引的判断
		//annotationConfigApplicationContextPart.refresh();













		//01.FactoryBean部分
		//AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//记得@ComponentScan

		//DaoFactoryBean daoFactoryBean= (DaoFactoryBean) annotationConfigApplicationContext.getBean("&daoFactoryBean");//使用FactoryBean的名字前加&获取到的bean是FactoryBean本身
		//daoFactoryBean.testBean();

		//TestDaoFactory testDaoFactory = (TestDaoFactory) annotationConfigApplicationContext.getBean("daoFactoryBean");//使用FactoryBean的名字获取到的bean是getObject()返回的bean--testDaoFactory
		//testDaoFactory.test();

		//TestDaoFactory testDaoFactory = (TestDaoFactory) annotationConfigApplicationContext.getBean("testDaoFactory");//直接使用对应beanName也可获取到testDaoFactory
		//testDaoFactory.test();

		//PrototypeDao zhangsan = (PrototypeDao) annotationConfigApplicationContext.getBean("zhangsan");
		//zhangsan.input();
	}

}
