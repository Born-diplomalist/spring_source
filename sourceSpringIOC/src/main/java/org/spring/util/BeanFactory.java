package org.spring.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeanFactory {
	/**
	 *
	 */
	Map<String,Object> map = new HashMap<String,Object>();
	public BeanFactory(String xml){
		parseXml(xml);
	}

	public void parseXml(String xml) throws LubanSpringException{

		File file = new File(this.getClass().getResource("/").getPath()+"//"+xml);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			Element elementRoot = document.getRootElement();
			Attribute attribute = elementRoot.attribute("default");
			boolean flag=false;
			if (attribute!=null){
				flag=true;
			}
			for (Iterator<Element> itFirlst = elementRoot.elementIterator(); itFirlst.hasNext();) {
				/**
				 * setup1、实例化对象
				 */
				Element elementFirstChil = itFirlst.next();
				Attribute attributeId = elementFirstChil.attribute("id");
				String beanName = attributeId.getValue();
				Attribute attributeClass = elementFirstChil.attribute("class");
				String clazzName  = attributeClass.getValue();
				Class clazz = Class.forName(clazzName);

				/**
				 * Setup2、 维护依赖关系
				 * 看这个对象有没有依赖（判断bean是否有property。或者判断类是否有对应的属性）
				 * 如果有则注入
				 */
				Object object = null;
				for (Iterator<Element> itSecond = elementFirstChil.elementIterator(); itSecond.hasNext();){
					// 得到ref的value，通过value得到对象（map）
					// 得到name的值，然后根据值获取一个Filed的对象
					//通过field的set方法set那个对象

					Element elementSecondChil = itSecond.next();
					//property标签意味着使用Setter注入
					if(elementSecondChil.getName().equals("property")){
						//需借助空参构造生成当前对象
						object= clazz.newInstance();
						String refValue = elementSecondChil.attribute("ref").getValue();
						Object injectObject= map.get(refValue) ;//从容器中根据ref所对应bean标签的id获取要注入的对象
						String nameValue = elementSecondChil.attribute("name").getValue();
						Field field = clazz.getDeclaredField(nameValue);
						field.setAccessible(true);
						field.set(object,injectObject);
					}
					//constructor-arg意味着使用构造方法注入
					else if(elementSecondChil.getName().equals("constructor-arg")){
						//使用非空参构造
						//此处可以不使用ref，而是使用constructor-arg标签的name，与之对应的是类中定义的属性名。
						String refValue = elementSecondChil.attribute("ref").getValue();
						Object injetObject= map.get(refValue) ;
						Class injectObjectClazz = injetObject.getClass();
						Constructor constructor = clazz.getConstructor(injectObjectClazz.getInterfaces()[0]);
						object = constructor.newInstance(injetObject);
					}
					else{
						throw new LubanSpringException("标签书写错误");
					}

				}
				//object依然为null，说明setter、constructor等手动注入方式未成功，此时才开始尝试自动注入
				// （手动注入优先级高于自动注入），如果手动注入成功则不再执行自动注入
				if(object==null) {
					//判断自动注入方式
					if (flag) {
						if (attribute.getValue().equals("byType")) {
							//判斷是否有依赖
							Field fields[] = clazz.getDeclaredFields();
							for (Field field : fields) {
								//得到属性的类型，比如String aa那麽這裏的field.getType()=String.class
								Class injectObjectClazz = field.getType();
								/**
								 * 由于是byType 所以无需考虑xml，只需要遍历map当中的所有对象
								 * 判断对象的类型是不是和属性类型相同
								 */
								int count = 0;
								Object injectObject = null;
								for (String key : map.keySet()) {
									Class temp = map.get(key).getClass().getInterfaces()[0];
									//发现map中有类型与要注入类型相同的
									if (temp.getName().equals(injectObjectClazz.getName())) {
										//记录一下找到的对象
										injectObject = map.get(key);
										//记录找到一个，因为可能找到多个count
										count++;
									}
								}

								if (count > 1) {
									throw new LubanSpringException("需要一个对象，但是找到了多个对象");
								} else {//满足条件，byType注入
									object = clazz.newInstance();
									field.setAccessible(true);
									field.set(object, injectObject);
								}
							}
						}
					}
				}
				//没有依赖注入，单纯地将bean放入容器
				if(object==null){//沒有子标签
					object = clazz.newInstance();
				}
				map.put(beanName,object);
				//org.springframework.beans.factory.BeanFactory beanFactor=new ClassPathXmlApplicationContext("xxx.xml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(map);
	}
	public Object getBean(String beanName){
		return map.get(beanName);
	}

}
