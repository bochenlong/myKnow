package org.bochenlong.mybatis;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.bochenlong.mybatis.bean.Address;
import org.bochenlong.mybatis.bean.Person;
import org.bochenlong.mybatis.config.MybatisConfig;
import org.bochenlong.print.PrintUt;

import java.util.List;

/**
 * Created by bochenlong on 16-11-9.
 */
public class MybatisNodeCommonMapper {
    public static void main(String[] args) {
//        insert();
        select();
    }

    public static void insert() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        try {
            PersonMapperCommonMapper personMapper = sqlSession.getMapper(PersonMapperCommonMapper.class);
            AddressMapperCommonMapper addressMapper = sqlSession.getMapper(AddressMapperCommonMapper.class);

            Person person = new Person();
            person.setRealName("bochenlong");
            person.setSex('1');
            person.setId(11);
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

    public static void select() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        try {
            PersonMapperCommonMapper personMapper = sqlSession.getMapper(PersonMapperCommonMapper.class);

            Person person = new Person();
            person.setRealName("bochenlong");
            person.setSex('1');
//            person.setId(11);


            List<Person> personList = personMapper.select(person);
            System.out.println(personList);
            int i = personMapper.selectCount(person);
            System.out.println(i);
            personList = personMapper.selectByRowBounds(person, new RowBounds(1, 2));
            System.out.println(personList);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
