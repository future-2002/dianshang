package com.tianshi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshi.pojo.Category;
import com.tianshi.service.CategoryService;
import com.tianshi.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/updateCategory")
public class UpdateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ServletInputStream is= req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.readValue(is, Category.class);
        CategoryService service = new CategoryServiceImpl();
        int result = service.updateCategory(category);
        resp.setContentType("application/json;charset=utf-8");
        if(result>0){
            mapper.writeValue(resp.getWriter(),"true");
            System.out.println("修改成功!");
        }else{
            mapper.writeValue(resp.getWriter(),"false");
            System.out.println("修改失败!");
        }
    }
}
