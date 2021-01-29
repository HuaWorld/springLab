package com.evan.xh.dao;

import com.evan.xh.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer>{

    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

}
