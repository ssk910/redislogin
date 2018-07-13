package com.hsk.redislogin.service;

import com.hsk.redislogin.model.Login;
import com.hsk.redislogin.model.User;

/**
 * 유저가 취할 수 있는 행동을 정의.
 *
 * @version     1.0 29 Jun 2018
 * @author      Seokgyu Hwang
 */
public interface UserService {
    /* 회원가입 */
    boolean register(User user);

    /* 회원가입 가능한 사용자인지 확인 */
    User validateUser(Login login);

    /* 회원 정보 수정 */
    boolean updateUser(User user);

    /* 계정 삭제 */
    boolean deleteUser(User user);
}
