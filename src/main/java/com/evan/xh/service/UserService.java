package com.evan.xh.service;

import com.evan.xh.dao.UserDao;
import com.evan.xh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean isExists(String username){
        User user = getByName(username);
        return null != user;
    }

    public User get(String username ,String password){
        return userDao.getByUsernameAndPassword(username,password);
    }

    public void add(User user){
        userDao.save(user);
    }

    public User getByName(String username){
        return userDao.findByUsername(username);
    }

}
