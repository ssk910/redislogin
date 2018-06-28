package com.hsk.redislogin.service;

import com.hsk.redislogin.model.Login;
import com.hsk.redislogin.model.User;

public interface UserService {
    boolean register(User user);
    User validateUser(Login login);
}
