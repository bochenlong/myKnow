package org.bochenlong.datasource;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bochenlong on 16-9-22.
 */
public class DataConfig {
    private static DataSource ds = init();

    private static DataSource init() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        ds.setJdbcUrl("jdbc:derby://localhost:1527//home/bochenlong/derbys/mydb");
        ds.setUsername("app");
        ds.setPassword("app");
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setJdbcUrl("jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false");
//        ds.setUsername("root");
//        ds.setPassword("root");
        ds.setConnectionTimeout(100000);// 连接获取超时时间
        ds.setIdleTimeout(600000);// idea连接超时时间
        // ds.setMaxLifetime();// sql wait_timeout 数据库连接最长时间没有connection则关闭
        ds.setMaximumPoolSize(3000);
        ds.setMinimumIdle(500);
        return ds;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
