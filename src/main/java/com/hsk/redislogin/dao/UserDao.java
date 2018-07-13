package com.hsk.redislogin.dao;

import com.hsk.redislogin.model.Login;
import com.hsk.redislogin.model.User;

public interface UserDao {
    /* 회원가입 */
    boolean register(User user);

    /* 회원가입 가능한 사용자인지 확인 */
    User validateUser(Login login);

    /* 회원 정보 수정 */
    boolean updateUser(User user);

    /* 계정 삭제 */
    boolean deleteUser(User user);
}
