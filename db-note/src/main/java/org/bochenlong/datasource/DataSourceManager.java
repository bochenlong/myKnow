package org.bochenlong.datasource;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bochenlong on 16-9-22.
 */
public class DataSourceManager {
    private static class Holder {
        private static DataSource dataSource = init();
    }

    private static DataSource instance() {
        return Holder.dataSource;
    }

    private static DataSource init() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

        ds.setConnectionTimeout(100000);
        ds.setIdleTimeout(600000);
        // ds.setMaxLifetime();// sql wait_timeout 数据库连接最长时间没有connection则关闭
        ds.setMaximumPoolSize(3000);
        ds.setMinimumIdle(500);

        String jdbcUrl = new StringBuilder()
                .append("jdbc:mysql://localhost:3306/myDb?")
                .append("useUnicode=true&characterEncoding=UTF8")
                .append("&serverTimezone=UTC")
                .append("&useSSL=false").toString();
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }


}
