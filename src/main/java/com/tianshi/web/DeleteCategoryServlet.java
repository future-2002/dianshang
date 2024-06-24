package com.tianshi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshi.service.CategoryService;
import com.tianshi.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CategoryService categoryService = new CategoryServiceImpl();
        int i = categoryService.deleteCategory(Integer.parseInt(id));
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        if (i > 0) {
            mapper.writeValue(resp.getWriter(), "true");
            System.out.println("删除成功");
        }else {
            mapper.writeValue(resp.getWriter(), "false");
            System.out.println("删除失败");
        }
    }
}
