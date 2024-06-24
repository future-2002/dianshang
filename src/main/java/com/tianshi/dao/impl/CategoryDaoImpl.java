package com.tianshi.dao.impl;

import com.tianshi.dao.CategoryDao;
import com.tianshi.pojo.Category;
import com.tianshi.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public int insertCategory(Category category) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into category(pid,catename,img) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,category.getPid());
        ps.setString(2,category.getCatename());
        ps.setString(3,category.getImg());
        int i = ps.executeUpdate();
        if (category == null) i=0;
        if (i>0) return i;
        JDBCUtils.close(null,ps,conn);
        return 0;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> list = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from category";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Category category = new Category(rs.getInt("id"),rs.getInt("pid"),rs.getInt("status"),rs.getString("catename"),rs.getString("img"));
            list.add(category);
        }
        JDBCUtils.close(null,ps,conn);
        return list;
    }

    @Override
    public int deleteCategory(int id) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from category where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        int i = ps.executeUpdate();
        if (i>0) return i;
        JDBCUtils.close(null,ps,conn);
        return 0;
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category = null;
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from category where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            category = new Category(rs.getInt("id"),rs.getInt("pid"),rs.getInt("status"),rs.getString("catename"),rs.getString("img"));
        }
        JDBCUtils.close(null,ps,conn);
        return category;
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update category set pid = ?,status = ?,catename = ?,img = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,category.getPid());
        ps.setInt(2,category.getStatus());
        ps.setString(3,category.getCatename());
        ps.setString(4,category.getImg());
        ps.setInt(5,category.getId());
        int i = ps.executeUpdate();
        if (i>0) return i;
        JDBCUtils.close(null,ps,conn);
        return 0;
    }
}
