package org.bochenlong.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.bochenlong.mybatis.config.MybatisConfig;

/**
 * Created by bochenlong on 16-11-9.
 */
public class MybatisNodeCommonMapper {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        PersonMapperCommonMapper mapper = sqlSession.getMapper(PersonMapperCommonMapper.class);

    }

    public static void insert() {
    }
}
