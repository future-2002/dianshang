package com.tianshi.web;

import com.tianshi.pojo.User;
import com.tianshi.service.UserService;
import com.tianshi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("registerUsername");
        String password = req.getParameter("registerPassword");
        String email = req.getParameter("registerEmail");
        User user = new User(username, password, email);
        UserService service = new UserServiceImpl();
        int result = service.register(user);
        if (result > 0) {
            System.out.println("注册成功");
            resp.sendRedirect("login.html");
        } else {
            System.out.println("注册失败");
        }
    }
}
