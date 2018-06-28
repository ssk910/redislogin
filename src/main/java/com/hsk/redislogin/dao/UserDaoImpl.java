package com.hsk.redislogin.dao;


import com.hsk.redislogin.config.RedisConfig;
import com.hsk.redislogin.model.Login;
import com.hsk.redislogin.model.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {
//    @Autowired
//    DataSource dataSource;
    RedisConfig redisConfig;


    Logger logger = Logger.getLogger(this.getClass().getName());

    public UserDaoImpl() {
        redisConfig = new RedisConfig();
    }

    @Override
    public boolean register(User user) {
        redisConfig.openConnection();

        if (redisConfig.hlen(user.getId()) > 0) {
            // 나중에 수정
            logger.log(Level.WARNING, "already exists.");
            //
            return false;
        }

        redisConfig.addHash(user.getId(), "id", user.getId());
        redisConfig.addHash(user.getId(), "pw", user.getPw());
        redisConfig.addHash(user.getId(), "name", user.getName());
        redisConfig.addHash(user.getId(), "email", user.getEmail());
        logger.log(Level.INFO, "register complete.");
        logger.log(Level.INFO, "[id] " + user.getId());
        logger.log(Level.INFO, "[pw] " + user.getPw());
        logger.log(Level.INFO, "[name] " + user.getName());
        logger.log(Level.INFO, "[email] " + user.getEmail());

        redisConfig.closeConnection();
        return true;
    }

    @Override
    public User validateUser(Login login) {
        boolean isValidId;
        boolean isValidPw;
        String givenId;
        String givenPw;
        User user = null;

        redisConfig.openConnection();

        logger.info("given Id: " + login.getId());
        logger.info("given Pw: " + login.getPw());

        givenId = redisConfig.getHash(login.getId(), "id");
        givenPw = redisConfig.getHash(login.getId(), "pw");

        givenId = givenId == null ? "" : givenId;
        givenPw = givenPw == null ? "" : givenPw;

        isValidId = givenId.compareTo(login.getId()) == 0 ? true : false;
        isValidPw = givenPw.compareTo(login.getPw()) == 0 ? true : false;

        if (!isValidId) {
            logger.log(Level.WARNING, "LogIn Failed : invalid ID");
        } else if (!isValidPw) {
            logger.log(Level.WARNING, "LogIn Failed : invalid Password");
        } else if (!(isValidId && isValidPw)) {
            logger.log(Level.WARNING, "LogIn Failed : invalid Id and Password");
        } else {
            user = new User(
//                redisConfig.getHash(login.getId(), "id"),
//                redisConfig.getHash(login.getId(), "pw"),
//                redisConfig.getHash(login.getId(), "name"),
//                redisConfig.getHash(login.getId(), "meail")

                redisConfig.getHash(login.getId(), "id"),
                redisConfig.getHash(login.getId(), "pw"),
                redisConfig.getHash(login.getId(), "name"),
                redisConfig.getHash(login.getId(), "email")
            );

            // 나중에 json 형식으로 수정.
            // key : "users"
            // field : id | pw | name | email
            // value : {[ ... ]}

            logger.info("created User instance.");
        }

        redisConfig.closeConnection();
        return user;
    }
}
