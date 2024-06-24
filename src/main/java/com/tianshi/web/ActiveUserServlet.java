package com.tianshi.web;

import com.tianshi.service.UserService;
import com.tianshi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/activeUser")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取激活码
        String code = req.getParameter("code");
        //根据激活码查询用户信息
        UserService Service = new UserServiceImpl();
        int result = Service.activeUser(code);
    }
}
