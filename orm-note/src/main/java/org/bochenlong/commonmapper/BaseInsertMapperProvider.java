package org.bochenlong.commonmapper;

import org.apache.ibatis.mapping.MappedStatement;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by bochenlong on 16-10-29.
 */
public class BaseInsertMapperProvider extends AbstractMapperProvider {

    /*没有实际用处*/
    public String dynamicSQL(Object record) {
        return "dynamicSQL";
    }

    /**
     * 插入，忽略数据库默认值，即空也插入
     *
     * @param ms
     * @return String
     */
    public String insert(MappedStatement ms) {
        Class clazz = getEntityClass(ms).get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(insertIntoTable(clazz));
        List<String> sql = columnAndProperty(clazz);
        join(stringBuilder, sql);
        return stringBuilder.toString();
    }

    /**
     * 根据属性是否为空插入
     *
     * @param ms
     * @return String
     */
    public String insertSelective(MappedStatement ms) {
        Class clazz = getEntityClass(ms).get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(insertIntoTable(clazz));
        List<String> sql = columnsAndPropertyIfNotNull(clazz);
        join(stringBuilder, sql);
        return stringBuilder.toString();
    }

    private static String insertIntoTable(Class<?> clazz) {
        StringJoiner stringJoiner = new StringJoiner(" ", "", "\n");
        return stringJoiner.add("insert into").add(entityTableMap.get(clazz).getTableName()).toString();
    }

    private void join(StringBuilder stringBuilder, List<String> sql) {
        stringBuilder.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");
        stringBuilder.append(sql.get(0));
        stringBuilder.append("</trim>");
        stringBuilder.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");
        stringBuilder.append(sql.get(1));
        stringBuilder.append("</trim>");
    }

    private static List<String> columnsAndPropertyIfNotNull(Class<?> clazz) {
        StringJoiner columns = new StringJoiner(" ", "", "\n");
        StringJoiner values = new StringJoiner(" ", "", "\n");
        EntityTable entityTable = entityTableMap.get(clazz);
        entityTable.getPropertyColumn().entrySet()
                .stream()
                .forEach(a -> {
                    columns.add("<if test=\"" + BaseMapper.paramPre + a.getKey() + "!=null\">" + a.getValue() + ",</if>");
                    values.add("<if test=\"" + BaseMapper.paramPre + a.getKey() + "!=null\">#{" + BaseMapper.paramPre + a.getValue() + "},</if>");
                });
        return Arrays.asList(columns.toString(), values.toString());
    }

    public static List<String> columnAndProperty(Class<?> clazz) {
        StringJoiner columns = new StringJoiner(" ", "", " ");
        StringJoiner values = new StringJoiner(" ", "", " ");
        EntityTable entityTable = entityTableMap.get(clazz);
        entityTable.getPropertyColumn().entrySet()
                .stream()
                .forEach(a -> {
                    columns.add(BaseMapper.paramPre + a.getValue() + ",");
                    values.add("#{" + BaseMapper.paramPre + a.getKey() + "},");
                });
        return Arrays.asList(columns.toString(), values.toString());
    }

}
