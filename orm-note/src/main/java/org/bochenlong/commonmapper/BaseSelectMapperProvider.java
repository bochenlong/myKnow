package org.bochenlong.commonmapper;

import org.apache.ibatis.mapping.MappedStatement;

import java.util.List;

/**
 * Created by bochenlong on 16-11-2.
 */
public class BaseSelectMapperProvider extends AbstractMapperProvider {
    /*没有实际用处*/
    public String dynamicSQL(Object record) {
        return "dynamicSQL";
    }


    public String selectByRecord(MappedStatement ms) {
        Class clazz = getEntityClass(ms).get();
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }
}
