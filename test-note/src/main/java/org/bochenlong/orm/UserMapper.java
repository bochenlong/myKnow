package org.bochenlong.orm;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bochenlong on 16-10-11.
 */
public interface UserMapper extends IMybatisMapper {
    @Insert("insert into user_ values (#{user.id},#{user.name},#{user.password},#{user.createTime})")
    void insert(@Param("user") User user);

    void deleteAll();
}
