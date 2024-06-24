package com.tianshi.dao.impl;

import com.tianshi.dao.UserDao;
import com.tianshi.pojo.User;
import com.tianshi.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public int register(User user)throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into user(username, password, email,status,code) values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.setInt(4, user.getStatus());
        ps.setString(5, user.getCode());
        int result = ps.executeUpdate();
        if (result > 0) return result;
        JDBCUtils.close(null, ps, conn);
        return 0;
    }

    @Override
    public int activeUser(String code) throws SQLException{
        Connection conn = JDBCUtils.getConnection();
        String sql = "update user set status = 1 where code = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, code);
        if (ps.executeUpdate() > 0) return 1;
        JDBCUtils.close(null, ps, conn);
        return 0;
    }


    @Override
    public User login(String username, String password) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        if (rs.next()) {
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setStatus(rs.getInt("status"));
        }
        JDBCUtils.close(rs, ps, conn);
        return user;
    }

}
