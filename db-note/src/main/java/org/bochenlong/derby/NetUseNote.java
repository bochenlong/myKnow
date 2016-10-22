package org.bochenlong.derby;

import org.bochenlong.print.PrintUt;

import java.sql.*;

/**
 * Created by bochenlong on 16-10-22.
 */
public class NetUseNote {
    private static String DBLOCALPATH = "/home/bochenlong/derbys/nettestdb";
    private static String CREATETABLESQL = "create table testtable(id int)";
    private static String INSERTDATA = "insert into testtable values(1)";

    /**
     * 注意先开启网络监听，在${DERBYHOME}/bin/startNetworkServer
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            // 加载jdbc驱动
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // 连接到执行数据库 (create=True没有则创建)
            String url = "jdbc:derby://localhost:1527/" + DBLOCALPATH + ";create=True";
            // 用户默认为app/app 或者为空
            conn = DriverManager.getConnection(url, "app", "app");
            // 创建一张表
            statement = conn.createStatement();
            int i;
//            i = statement.executeUpdate(CREATETABLESQL);
//            PrintUt.print("--- 创建table" + i);
//            i = statement.executeUpdate(INSERTDATA);
//            PrintUt.print("--- 插入数据" + i);
            ResultSet rs = statement.executeQuery("SELECT count(*) as i FROM testtable");
            while (rs.next()) {
                i = rs.getInt("i");
                PrintUt.print("result : " + i);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
