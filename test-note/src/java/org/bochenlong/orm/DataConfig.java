package org.bochenlong.orm;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bochenlong on 16-9-22.
 */
public class DataConfig {
    private static DataSource ds = init();
    public static SqlSessionFactory sqlSessionFactory  = getSqlSessionFt();

    private static DataSource init() {
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

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SqlSessionFactory getSqlSessionFt() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, ds);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("org.bochenlong.orm");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
