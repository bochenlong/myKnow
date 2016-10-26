package org.bochenlong.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.jboss.C3P0PooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bochenlong on 16-9-22.
 */
public class DataConfig {
    public static DataSource hikari = initHikari();
    public static DataSource c3p0 = initC3p0();
    private static DataSource druid = initDruid();

    private static DataSource initHikari() {
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

    private static DataSource initC3p0() {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setMinPoolSize(50);
            ds.setMaxPoolSize(300);
            ds.setMaxIdleTime(60);
            ds.setMaxStatements(500);
            ds.setIdleConnectionTestPeriod(120);
            ds.setAcquireIncrement(10);
            ds.setCheckoutTimeout(180000);
            ds.setDriverClass("org.apache.derby.jdbc.ClientDriver");
            ds.setUser("app");
            ds.setPassword("app");
            ds.setJdbcUrl("jdbc:derby://localhost:1527//home/bochenlong/derbys/mydbnet");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return ds;
    }

    private static DataSource initDruid() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        ds.setUrl("jdbc:derby:/home/bochenlong/derbys/mydb");
        ds.setUsername("");
        ds.setPassword("");
        ds.setInitialSize(500);
        ds.setMinIdle(500);
        ds.setMaxActive(3000);
        ds.setMaxWait(60000);
        ds.setValidationQuery("values 1");
        ds.setValidationQueryTimeout(10);
        ds.setTimeBetweenEvictionRunsMillis(10000);
        ds.setMinEvictableIdleTimeMillis(100000);
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        ds.setRemoveAbandoned(true);
        ds.setRemoveAbandonedTimeout(200);
        return ds;
    }

    public static Connection getHikariConnection() {
        try {
            return hikari.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getC3p0Conection() {
        try {
            return c3p0.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getDruidConection() {
        try {
            return druid.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
