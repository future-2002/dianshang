package com.tianshi.service.impl;

import com.tianshi.dao.CategoryDao;
import com.tianshi.dao.impl.CategoryDaoImpl;
import com.tianshi.pojo.Category;
import com.tianshi.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public int insertCategory(Category category) {
        int result = 0;
        try {
            // 调用DAO层的插入方法
            result = categoryDao.insertCategory(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = null;
        try {
            list = categoryDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public int deleteCategory(int id) {
        int result = 0;
        try {
            result = categoryDao.deleteCategory(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try {
            category = categoryDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public int updateCategory(Category category) {
        int result = 0;
        try {
            result = categoryDao.updateCategory(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
