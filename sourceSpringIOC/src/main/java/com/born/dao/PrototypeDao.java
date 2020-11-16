package com.born.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @Description: 一个原型类
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 15:01:47
 */
@Repository("zhangsan")
@Scope("prototype")
public class PrototypeDao {

	private String name;

	public void input(){
		System.out.println("我来了！");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
