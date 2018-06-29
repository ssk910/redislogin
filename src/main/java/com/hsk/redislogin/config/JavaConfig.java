package com.hsk.redislogin.config;

import com.hsk.redislogin.dao.UserDao;
import com.hsk.redislogin.dao.UserDaoImpl;
import com.hsk.redislogin.service.UserService;
import com.hsk.redislogin.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class JavaConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

//    @Bean
//    public RequestMappingHandlerMapping useTrailingSlash() {
//        return new RequestMappingHandlerMapping() {{ setUseTrailingSlashMatch(true); }};
//    }
}
