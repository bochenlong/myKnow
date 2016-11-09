package org.bochenlong.mybatis.config;

import com.github.pagehelper.PageHelper;
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
import org.bochenlong.mybatis.IMybatis;
import tk.mybatis.mapper.code.Style;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.util.Properties;

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
        Environment environment = new Environment("development", transactionFactory, DataConfig.hikari);
        Configuration configuration = new Configuration(environment);
//        processConfiguration(configuration);
//        processConfiguration1(configuration);
        processConfiguration2(configuration);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    /**
     * normal
     */
    private static void processConfiguration(Configuration configuration) {
        configuration.addMappers("org.bochenlong.mybatis", IMybatis.class);
    }

    /**
     * yours
     *
     * @param configuration
     */
    private static void processConfiguration1(Configuration configuration) {
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

    /**
     * 通用mapper
     * @param configuration
     */
    private static void processConfiguration2(Configuration configuration) {
        configuration.addMappers("org.bochenlong.mybatis", Mapper.class);
        MapperHelper mapperHelper = new MapperHelper();
        Config config = new Config();
        config.setStyle(Style.normal);
        mapperHelper.setConfig(config);
        mapperHelper.registerMapper(Mapper.class);
        mapperHelper.processConfiguration(configuration);

        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "sqlserver2012");
        /**
         * 设置pageNum含义
         */
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "false");
        properties.setProperty("pageSizeZero", "false");
        /**
         * 不开启合理化配置
         * 但需要注意，RowBounds 0/负数都为1
         * */
        properties.setProperty("reasonable", "false");
        // 不启用PageHelper.start(Object params)
        // properties.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");

        properties.setProperty("supportMethodsArguments", "false");
        properties.setProperty("returnPageInfo", "none");

        pageHelper.setProperties(properties);
        configuration.addInterceptor(pageHelper);
    }
}
