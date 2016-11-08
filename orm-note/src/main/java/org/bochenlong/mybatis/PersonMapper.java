package org.bochenlong.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.bochenlong.mybatis.bean.Person;

import java.util.Map;

/**
 * Created by bochenlong on 16-10-26.
 */
public interface PersonMapper extends IMybatis {
    int insertSelective(Person person);

    @ResultType(Person.class)
    @Select("select * from person where id = #{id}")
    Person selectByPrimaryKey(@Param("id") int id);

    Person selectJoinByParamsMap(Map<String, Object> paramsMap);
}
