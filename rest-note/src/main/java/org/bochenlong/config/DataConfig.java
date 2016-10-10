package org.bochenlong.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by bochenlong on 16-9-22.
 */
public class DataConfig {
    private static DataSource ds = init();
    private static DataSource[] dses = init3();

    private static DataSource[] init3() {
        return new DataSource[]{init(), init2()};
    }

    private static DataSource init() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        ds.setJdbcUrl("jdbc:derby:/home/bochenlong/derbys/mydb");
        ds.setUsername("");
        ds.setPassword("");
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

    private static DataSource init2() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        ds.setUrl("jdbc:derby:/home/bochenlong/derbys/mydb1");
        ds.setUsername("");
        ds.setPassword("");
        ds.setInitialSize(500);
        ds.setMinIdle(500);
        ds.setMaxActive(3000);
        ds.setMaxWait(60000);
//        ds.setValidationQuery("values 1");
//        ds.setValidationQueryTimeout(10);
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        return ds;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
//            return dses[new Random().nextInt(2)].getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
