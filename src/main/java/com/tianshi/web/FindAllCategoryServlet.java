package com.tianshi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshi.pojo.Category;
import com.tianshi.service.CategoryService;
import com.tianshi.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAllCategory")
public class FindAllCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("============findAllCategory===============");
        CategoryService service = new CategoryServiceImpl();
        List<Category> list = service.findAll();
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), list);
    }
}
