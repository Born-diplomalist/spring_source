package com.born.dao;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *
 * 如果类A实现了FactoryBean
 * 那么Spring容器中即存在getObject()返回的对象又存在类A的对象
 * 其中类A的对象名是 &加上类名，getObject()的对象就是对应的类名
 *
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 08:36:59
 */

@Component
public class DaoFactoryBean implements FactoryBean {

    public void testBean(){
        System.out.println("testBean");
    }


    @Override
    public Object getObject() throws Exception {
        return new TestDaoFactory();
    }

    @Override
    public Class<?> getObjectType() {
        return TestDaoFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
