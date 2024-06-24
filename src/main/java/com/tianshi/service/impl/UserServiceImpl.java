package com.tianshi.service.impl;

import com.tianshi.dao.UserDao;
import com.tianshi.dao.impl.UserDaoImpl;
import com.tianshi.pojo.User;
import com.tianshi.service.UserService;
import com.tianshi.util.MailUtils;
import com.tianshi.util.Md5Util;
import com.tianshi.util.UuidUtil;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public int register(User user) {
        int result = 0;//代表返回值
        try {
            //补全属性
            user.setStatus(0);//用户注册，但是没有激活
            user.setCode(UuidUtil.getUuid());//设置激活码
            //对用户的密码进行加密存储
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
            result = userDao.register(user);
            //需要发生一个激活邮件到我们注册时候的邮箱
            String link = "<a href='http://localhost:8080/Web_Project/activeUser?code="+user.getCode()+"'>点击激活账户</a>";
            MailUtils.sendMail("future-2002@qq.com",link,"账户激活邮件");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int activeUser(String code) {
        int result = 0;
        try {
            result = userDao.activeUser(code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = userDao.login(username,Md5Util.encodeByMd5(password));
             if(user != null){
                 return user;
             }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
