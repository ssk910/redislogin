package com.hsk.redislogin.service;

import com.hsk.redislogin.dao.UserDao;
import com.hsk.redislogin.model.Login;
import com.hsk.redislogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }

    @Override
    public User validateUser(Login login) {
        return userDao.validateUser(login);
    }
}
