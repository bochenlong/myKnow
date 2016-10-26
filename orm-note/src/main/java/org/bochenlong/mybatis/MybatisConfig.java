package org.bochenlong.mybatis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.bochenlong.datasource.DataConfig;

/**
 * Created by bochenlong on 16-9-22.
 */
public class MybatisConfig {
    public static SqlSessionFactory sqlSessionFactory = getSqlSessionFt();

    private static SqlSessionFactory getSqlSessionFt() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, DataConfig.c3p0);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("org.bochenlong", IMybatisMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
