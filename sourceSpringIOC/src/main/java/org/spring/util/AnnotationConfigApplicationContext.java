package org.spring.util;

import com.born.anno.Luban;

import java.io.File;

public class AnnotationConfigApplicationContext {
	/**
	 * 对指定路径下所有类进行扫描，扫描内容可以是是否加了特定的注解或者是否实现了特定的接口等
	 * @param basePackage
	 */
	public void scan(String basePackage){
		//生产环境下都是把编译完的源码作部署的，是没有com.xxx目录的的，都是classes目录下的class，
		String  rootPath=this.getClass().getResource("/").getPath();
		//将路径字符串中的点替换为\\
		String basePackagePath = basePackage.replaceAll("\\.", "\\\\");
		File file = new File(rootPath + "//" + basePackage);
		//此处本应该递归以防止还有二级子目录，在此不做递归，仅做简单模拟
		String[] names = file.list();
		for (String name : names) {
			//根据class文件名获取类名
			name=name.replaceAll(".class",".");
			try {
				Class<?> clazz = Class.forName(basePackage + "." + name);
				//判断类上是否加了@Luban注解
				if (clazz.isAnnotationPresent(Luban.class)) {
					Luban luban = clazz.getAnnotation(Luban.class);
					System.out.println(luban.value());
					System.out.println(clazz.newInstance());
				}
				//如果想判断@Autowired，可以clazz.getDeclaredFields()得到所有变量，判断变量上是否有对应注解
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}

	}


    //public void scan(String basePackage){
    //    String rootPath = this.getClass().getResource("/").getPath();
    //    String  basePackagePath =basePackage.replaceAll("\\.","\\\\");
    //    File file = new File(rootPath+"//"+basePackagePath);
    //    String names[]=file.list();
    //    for (String name : names) {
    //        name=name.replaceAll(".class","");
    //        try {
	//
    //           Class clazz =  Class.forName(basePackage+"."+name);
    //            //判斷是否是属于@service@xxxx
    //            if(clazz.isAnnotationPresent(Luban.class)){
    //                Luban luban = (Luban) clazz.getAnnotation(Luban.class);
    //                System.out.println(luban.value());
    //                System.out.println(clazz.newInstance());
	//
    //            }
	//
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
	//
    //}
}
