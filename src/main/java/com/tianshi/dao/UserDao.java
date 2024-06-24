package com.tianshi.dao;

import com.tianshi.pojo.User;

import java.sql.SQLException;

public interface UserDao {
    // 注册
    int register(User user) throws SQLException;

    int activeUser(String code) throws SQLException;

    User login(String username, String password) throws SQLException;
}
