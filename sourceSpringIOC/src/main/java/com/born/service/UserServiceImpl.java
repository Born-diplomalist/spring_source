package com.born.service;

import com.born.anno.Luban;
import com.born.dao.UserDao;

@Luban("bb")
public class UserServiceImpl implements UserService {


    UserDao dao;

    @Override
    public void find() {
        System.out.println("service");
        dao.query();
    }

    //public void setDao(UserDao dao) {
       // this.dao = dao;
   // }
}
