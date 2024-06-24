package com.tianshi.service;

import com.tianshi.pojo.Category;

import java.util.List;

public interface CategoryService {
    int insertCategory(Category category);
    List<Category> findAll();
    int deleteCategory(int id);
    Category findById(int id);
    int updateCategory(Category category);
}
