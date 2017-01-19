package org.bochenlong.commonmapper;

import org.apache.ibatis.mapping.MappedStatement;

import java.beans.Transient;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by bochenlong on 16-10-29.
 */
abstract class AbstractMapperProvider {
    /**
     * switch the property custom
     */
    protected static Entity2TableStyle style = Entity2TableStyle.KEEP;

    protected static Map<String, Class<?>> entityClassMap = new ConcurrentHashMap<>();

    protected static Map<Class<?>, EntityTable> entityTableMap = new ConcurrentHashMap<>();

    /**
     * 获取实体的class类型
     *
     * @param ms
     * @return Optional<Class<?>>
     */
    protected static Optional<Class<?>> getEntityClass(MappedStatement ms) {
        String msId = ms.getId();
        // 优先从缓存里获取
        Class<?> entityClass = entityClassMap.get(msId);
        if (entityClass != null) {
            return Optional.of(entityClass);
        } else {
            // 否则需要处理
            Class<?> mapperClass = getMapperClass(msId).get();
            Type[] types = mapperClass.getGenericInterfaces();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType t = (ParameterizedType) type;
                    entityClass = (Class<?>) t.getActualTypeArguments()[0];
                    entityResolve(entityClass);
                    entityClassMap.put(msId, entityClass);
                    return Optional.of(entityClass);
                }
            }
        }
        throw new RuntimeException("can't get the entity class");
    }

    /**
     * 根据msId获取接口类
     *
     * @param msId
     * @return Optional<Class<?>>
     */
    private static Optional<Class<?>> getMapperClass(String msId) {
        if (msId.indexOf(".") == -1) {
            return Optional.empty();
        }
        String mapperClass = msId.substring(0, msId.lastIndexOf("."));
        try {
            return Optional.of(Class.forName(mapperClass));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }


    /**
     * 实体解析
     *
     * @param entity
     */
    private static void entityResolve(Class<?> entity) {
        EntityTable entityTable = new EntityTable();
        entityTable.setTableName(transform(style, entity.getSimpleName()));
        entityTable.setTableName(transform2Hump(entity.getSimpleName()));
        Arrays.asList(entity.getDeclaredFields()).stream()
                .filter(a -> a.getAnnotation(Transient.class) == null)
                .forEach(a -> entityTable.addBeanColumn(a.getName(), transform(style, a.getName())));
        entityTableMap.put(entity, entityTable);
    }

    private static String transform(Entity2TableStyle style, String string) {
        if (style.equals(Entity2TableStyle.HUMP)) {
            return transform2Hump(string);
        } else if (style.equals(Entity2TableStyle.KEEP)) {
            return string;
        }
        throw new RuntimeException("no support AbstractMapperProvider.style");
    }

    /**
     * 驼峰转换
     *
     * @param string
     * @return String
     */
    private static String transform2Hump(String string) {
        assert string != null && string.length() != 0;
        final boolean[] firstFlag = {true};
        return Arrays.asList(string.split(""))
                .stream()
                .map(a -> a.split(""))
                .flatMap(Arrays::stream)
                .map(a -> {
                    if (firstFlag[0] == true) {
                        a = a.toLowerCase();
                        firstFlag[0] = false;
                        return a;
                    } else if (Character.isUpperCase(a.charAt(0))) {
                        a = "_" + a.toLowerCase();
                        return a;
                    } else {
                        return a;
                    }
                }).collect(Collectors.joining());
    }

    /**
     * 实体解析类型
     */
    private enum Entity2TableStyle {
        KEEP,// 一致
        HUMP // 驼峰
    }
}
