package org.bochenlong.commonmapper;

import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by bochenlong on 16-11-2.
 */
public interface BaseSelectMapper<T> extends BaseMapper<T> {

    @SelectProvider(type = BaseSelectMapperProvider.class, method = "dynamicSQL")
    T selectByRecord(T t);

}
