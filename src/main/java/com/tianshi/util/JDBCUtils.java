package com.tianshi.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

//工具类-配置文件效率优化
public class JDBCUtils {
    //私有化构造方法
    private JDBCUtils(){}
    //定义Properties的变量
    private static Properties prop =null;
    //static代码块  在类加载时执行一次--完成读取配置文件
    static{
        prop = new Properties();
        //加载配置文件
        try {
            prop.load(JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取数据库连接
    public static Connection getConnection(){
        Connection conn = null;
        try{
            //加载驱动
            Class.forName(prop.getProperty("driver"));
            //获取连接对象
            conn = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("user"),
                    prop.getProperty("password")
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    //释放资源工具方法
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        try{
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCUtils.getConnection();

    }
}
