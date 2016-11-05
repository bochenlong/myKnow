package org.bochenlong.mybatis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.bochenlong.commonmapper.BaseMapper;
import org.bochenlong.commonmapper.BaseMapperHelper;
import org.bochenlong.datasource.DataConfig;

/**
 * Created by bochenlong on 16-9-22.
 */
public class MybatisConfig {
    public static SqlSessionFactory sqlSessionFactory = init();

    /**
     * 配置SqlSessionFactory
     *
     * @return
     */
    private static SqlSessionFactory init() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, DataConfig.c3p0);
        Configuration configuration = new Configuration(environment);
        processConfiguration(configuration);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    /**
     * 配置Mybatis
     *
     * @param configuration
     */
    private static void processConfiguration(Configuration configuration) {
        configuration.addMappers("org.bochenlong.mybatis", BaseMapper.class);
        if (configuration.hasMapper(BaseMapper.class)) {
            throw new RuntimeException("BaseMapper.class should exclude ; check the package");
        }
        configuration.getMappedStatements()
                .stream()
                .filter(a -> {
                    String msId = a.getId();
                    return BaseMapper.baseMethod.contains(msId.substring(msId.lastIndexOf(".") + 1));
                }).forEach(BaseMapperHelper::setSqlSource);
    }
}
