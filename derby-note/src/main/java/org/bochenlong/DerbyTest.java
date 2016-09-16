package org.bochenlong;

import org.bochenlong.print.PrintUt;

import java.sql.*;

/**
 * Created by bochenlong on 2016/9/15.
 */
public class DerbyTest {
    private static String DBPATH = "D:\\develop\\db-derby-10.12.1.1-bin\\dbs\\mydb";
    private static String DBURL = "jdbc:derby://localhost:1527/"+ DBPATH;

    public static void main(String[] args) {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //连接指定的derby数据库
            Connection conn = DriverManager.getConnection("jdbc:derby:" + DBPATH, "", "");
            doTest(conn);
            //关闭已经连接的数据库
            //Derby会抛出一个错误码为XJ015的异常表示关闭成功，应用程序可以不处理这两个异常。
            //DriverManager.getConnection("jdbc:derby:;shutdown=True");

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(DBURL,"","");
            doTest(conn);
            DriverManager.getConnection("jdbc:derby:;shutdown=True");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doTest(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT count(*) as i FROM mytable");
        while (rs.next()) {
            int i = rs.getInt("i");
            PrintUt.print("result : " + i);
        }
    }
}
