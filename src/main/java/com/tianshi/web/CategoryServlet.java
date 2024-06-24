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

@WebServlet("/addCategory")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求的字符编码为UTF-8，确保处理中文字符不出现乱码
        req.setCharacterEncoding("UTF-8");
        // 获取请求的输入流，用于读取前端发送的数据
        ServletInputStream is = req.getInputStream();
        // 创建 ObjectMapper 实例，用于将 JSON 数据映射为 Java 对象
        ObjectMapper mapper = new ObjectMapper();
        // 从输入流中读取 JSON 数据，并映射为 Category 类的对象
        Category category = mapper.readValue(is, Category.class);
        // 创建 CategoryService 的实现类实例，用于处理分类业务逻辑
        CategoryService categoryService = new CategoryServiceImpl();
        // 调用插入分类的方法，将 Category 对象插入数据库
        // 返回值为插入操作的影响行数
        int result = categoryService.insertCategory(category);
        // 设置响应内容类型为JSON
        resp.setContentType("application/json;character:utf-8");
        if (result > 0) {
            mapper.writeValue(resp.getWriter(), "true");
            System.out.println("添加成功");
        } else {
            mapper.writeValue(resp.getWriter(), "false");
            System.out.println("添加失败");
        }
    }
}
