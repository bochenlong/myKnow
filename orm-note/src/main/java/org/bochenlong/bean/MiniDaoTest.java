//package org.bochenlong;
//
//import org.bochenlong.print.PrintUt;
//import org.midao.jdbc.core.MjdbcFactory;
//import org.midao.jdbc.core.handlers.input.named.MapInputHandler;
//import org.midao.jdbc.core.handlers.input.named.MapListInputHandler;
//import org.midao.jdbc.core.handlers.output.MapListOutputHandler;
//import org.midao.jdbc.core.handlers.output.MapOutputHandler;
//import org.midao.jdbc.core.handlers.output.lazy.BeanLazyOutputHandler;
//import org.midao.jdbc.core.service.QueryRunnerService;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by bochenlong on 2016/9/16.
// */
//public class MiniDaoTest {
//    private static String DBPATH = "D:\\develop\\db-derby-10.12.1.1-bin\\dbs\\mydb";
//
//    public static void main(String[] args) {
//        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            QueryRunnerService runner = MjdbcFactory.getQueryRunner(getConn());
//            // 查询表 count
//            MapInputHandler input = new MapInputHandler("select count(*) count from mytable", null);
//            Map<String, Object> result = runner.query(input, new MapOutputHandler());// 此处传出为Map
//            PrintUt.print(result);
//
//            // 查询表数据 多列
//            // 设置查询参数，id -> :id
//            Map<String, Object> queryParameters = new HashMap<>();
//            queryParameters.put("id", 1);
//            input = new MapInputHandler("select id,name from mytable where id = :id", queryParameters);
//            List<Map<String, Object>> resultList = runner.query(input, new MapListOutputHandler());// 此处传出为MapList
//            PrintUt.print(resultList);
//            // 设置多map参数
//            Map<String, Object> queryParameters1 = new HashMap<>();
//            queryParameters1.put("id", 1);
//            Map<String, Object> queryParameters2 = new HashMap<>();
//            queryParameters2.put("id", 2);
//            Map<String, Map<String, Object>> queryParameters3 = new HashMap() {
//                {
//                    put("queryParameters1", queryParameters1);
//                    put("queryParameters2", queryParameters2);
//                }
//            };
//            MapListInputHandler inputList = new MapListInputHandler(
//                    "SELECT name FROM mytable WHERE id = :queryParameters1.id or id = :queryParameters2.id",
//                    queryParameters3);
//            resultList = runner.query(inputList, new MapListOutputHandler());
//            PrintUt.print(resultList);
//
//            // 查询结果封装到实体
//            runner.setTransactionManualMode(true);
//            input = new MapInputHandler("select * from myTable", null);
//            // 注意必须声明无参构造器
//            BeanLazyOutputHandler lazyBeanList = runner.query(input, new BeanLazyOutputHandler<>(MyTable.class));
//            List<MyTable> myTables = new ArrayList<>();
//            while (lazyBeanList.hasNext()) {
//                myTables.add((MyTable) lazyBeanList.getNext());
//            }
//            myTables.stream().forEach(System.out::println);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Connection getConn() throws SQLException {
//        //连接指定的derby数据库
//        Connection conn = DriverManager.getConnection("jdbc:derby:" + DBPATH, "", "");
//        return conn;
//    }
//}
