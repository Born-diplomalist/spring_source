package com.born.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-01 08:39:35
 */
@Service
public class TestDaoFactory {

    public void test(){
        System.out.println("factory bean");
    }

}
