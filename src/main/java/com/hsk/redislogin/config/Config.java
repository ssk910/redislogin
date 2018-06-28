package com.hsk.redislogin.config;

import com.hsk.redislogin.dao.UserDao;
import com.hsk.redislogin.dao.UserDaoImpl;
import com.hsk.redislogin.service.UserService;
import com.hsk.redislogin.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }
}
