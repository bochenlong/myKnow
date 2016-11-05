package org.bochenlong.commonmapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by bochenlong on 16-11-2.
 */
public interface BaseInsertMapper<T> {
    @SelectProvider(type = BaseInsertMapperProvider.class, method = "dynamicSQL")
    void insert(@Param("entity") T entity);

    @SelectProvider(type = BaseInsertMapperProvider.class, method = "dynamicSQL")
    void insertSelective(@Param("entity") T entity);
}
