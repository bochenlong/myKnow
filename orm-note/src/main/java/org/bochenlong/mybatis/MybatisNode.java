package org.bochenlong.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.bochenlong.bean.mybatis.Location;
import org.bochenlong.bean.mybatis.Useru;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bochenlong on 16-10-26.
 */
public class MybatisNode {
    public static void main(String[] args) {
        insert();
//        selectByParamsMap();
    }

    public static void insert() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        try {
            UseruMapper userMapper = sqlSession.getMapper(UseruMapper.class);
            Useru user = new Useru();
            user.setId(102);
            user.setFullname("abc");
            Location location = new Location();
            location.setId(102);
            location.setLocation("abc");
            location.setUserId(102);
            userMapper.insertLocation(location);
            userMapper.insertSelective(user);
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 联合查询
     */
    public static void selectByParamsMap() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        UseruMapper userMapper = sqlSession.getMapper(UseruMapper.class);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userId",1);
        List<Useru> list = userMapper.selectByParamsMap(paramsMap);
        System.out.println(list.size());
    }
}
