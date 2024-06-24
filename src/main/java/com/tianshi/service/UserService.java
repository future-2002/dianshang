package com.tianshi.service;

import com.tianshi.pojo.User;

import java.sql.SQLException;

public interface UserService {
    // 注册
    int register(User user);
    // 激活
    int activeUser(String code);
    // 登录
    User login(String username, String password);
}
