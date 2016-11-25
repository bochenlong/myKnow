package org.bochenlong.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.bochenlong.mybatis.bean.Address;
import org.bochenlong.mybatis.bean.Person;
import org.bochenlong.mybatis.config.MybatisConfig;
import org.bochenlong.print.PrintUt;

import java.util.*;

/**
 * Created by bochenlong on 16-10-26.
 */
public class MybatisNode {
    public static void main(String[] args) {
        insertSelective();
//        selectByPrimaryKey();
//        selectJoinByParamsMap();
//        deleteByPrimaryKeys();
    }


    public static void insertSelective() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        try {
            Person person = new Person();
            person.setRealName("bochenlong");
            person.setSex('1');
            // 默认 返回更新条数
            int id = personMapper.insertSelective(person);

            Address address = new Address();
            address.setCity("beijing");
            address.setProvince("beijing");
            // 通过mapper实现返回person id
            address.setPersonId(person.getId());
            PrintUt.print("id is ", person.getId());

            addressMapper.insertSelective(address);


            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }


    public static void selectByPrimaryKey() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        try {
            Person person = personMapper.selectByPrimaryKey(1);
            PrintUt.print("person is", person);
        } finally {
            sqlSession.close();
        }
    }

    public static void selectJoinByParamsMap() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        try {
            Map<String, Object> map = new HashMap() {{
//                put("personId", 1);
                put("personIds",Arrays.asList(3,4,5));
            }};

            List<Person> person = personMapper.selectJoinByParamsMap(map);
            PrintUt.print("person is", person);
        } finally {
            sqlSession.close();
        }
    }

    public static void deleteByPrimaryKeys() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        try {
            List<Integer> ids = Arrays.asList(1, 2);
            int i = personMapper.deleteByPrimaryKeys(ids);
            PrintUt.print("delete is ", i);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


}
