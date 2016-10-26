package org.bochenlong.mybatis;

import org.bochenlong.bean.mybatis.Location;
import org.bochenlong.bean.mybatis.Useru;

import java.util.List;
import java.util.Map;

/**
 * Created by bochenlong on 16-10-26.
 */
public interface UseruMapper extends IMybatisMapper {
    void insertUser(Useru user);

    void insertLocation(Location location);

    List<Useru> selectByParamsMap(Map<String, Object> map);
}
