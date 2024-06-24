package com.tianshi.dao;

import com.tianshi.pojo.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    int insertCategory(Category category)throws SQLException;
    List<Category> findAll()throws SQLException;
    int deleteCategory(int id)throws SQLException;
    Category findById(int id)throws SQLException;
    int updateCategory(Category category)throws SQLException;
}
