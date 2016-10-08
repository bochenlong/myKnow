package org.bochenlong.config;

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
        ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        ds.setJdbcUrl("jdbc:derby:/home/bochenlong/derbys/mydb");
        ds.setUsername("");
        ds.setPassword("");
        ds.setConnectionTimeout(100000);// 连接获取超时时间
        ds.setIdleTimeout(600000);// idea连接超时时间
        // ds.setMaxLifetime();// sql wait_timeout 数据库连接最长时间没有connection则关闭
        ds.setMaximumPoolSize(5000);
        ds.setMinimumIdle(5000);
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
