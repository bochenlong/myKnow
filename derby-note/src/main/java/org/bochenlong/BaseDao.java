package org.bochenlong;

import org.bochenlong.print.PrintUt;
import org.midao.jdbc.core.MjdbcFactory;
import org.midao.jdbc.core.exception.ExceptionHandler;
import org.midao.jdbc.core.handlers.input.named.MapInputHandler;
import org.midao.jdbc.core.handlers.output.lazy.BeanLazyOutputHandler;
import org.midao.jdbc.core.service.QueryRunnerService;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bochenlong on 2016/9/19.
 */
public interface BaseDao<T> {
    String DBPATH = "D:\\develop\\db-derby-10.12.1.1-bin\\dbs\\mydb";

    T save(T t);

    default T saveDefault(T t) {
        assert t != null; // t不为空

        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        assert fields != null && fields.length != 0; // t属性不为空

        try {
            String columns = Arrays.stream(fields).filter(f -> {
                try {
                    return f.get(t) == null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return true;
            }).map(Field::getName).collect(Collectors.joining(","));


            QueryRunnerService queryRunnerService = null;
            queryRunnerService = generateRunner();
`
            queryRunnerService.setTransactionManualMode(true);

            MapInputHandler input = new MapInputHandler(sb.toString(), paramMap);
            Object o = queryRunnerService.update(input, new BeanLazyOutputHandler<>(clazz));
            queryRunnerService.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (T) o;

    }

    default QueryRunnerService generateRunner() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:" + DBPATH, "", "");
        return MjdbcFactory.getQueryRunner(conn);

    }

    public static void main(String[] args) {
        DemoBean db = new DemoBean();
//        db.setId(1);
//        db.setName("bochenlong");
//        BaseDao<DemoBean> b = new BaseDao<DemoBean>() {
//            @Override
//            public DemoBean save(DemoBean db) {
//                return this.saveDefault(db);
//            }
//        };
//        db = b.saveDefault(db);
//        System.out.println(db.getCreateTime());
    }
}
