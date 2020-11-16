package com.born.service;

import com.born.dao.SingleDaoWithPrototype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-06-25 11:23:22
 */
@Service
public class TestService {

	@Autowired
	private SingleDaoWithPrototype singleDaoWithPrototype;

	void query(){
		singleDaoWithPrototype.query();

	}

}
