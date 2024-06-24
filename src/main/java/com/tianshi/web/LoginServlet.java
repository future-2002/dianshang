package com.tianshi.web;

import com.tianshi.pojo.User;
import com.tianshi.service.UserService;
import com.tianshi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("loginUsername");
        String password = req.getParameter("loginPassword");
        UserService service = new UserServiceImpl();
        User user = service.login(username, password);
        if (user == null) {
            System.out.println("登录失败，用户名或密码错误");
            resp.sendRedirect("login.html");
        }else if (user.getStatus() == 0) {
            System.out.println("登录失败，用户状态不正确");
            resp.sendRedirect("login.html");
        }else {
            System.out.println("登录成功");
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);
            resp.sendRedirect("cate.html");
        }
    }
}
