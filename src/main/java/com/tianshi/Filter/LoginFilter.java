package com.tianshi.Filter;

import com.tianshi.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        String uri = req.getRequestURI();
        if (user != null ||uri.contains("login")||uri.contains("register")||uri.contains("css")||uri.contains("js")||uri.contains("activeUser")) {
            System.out.println("==========放行操作===========");
            chain.doFilter(request, response);
        } else {
            System.out.println("您未登录，请登录......");
            resp.sendRedirect( "login.html");
        }
    }

    @Override
    public void destroy() {
    }
}
