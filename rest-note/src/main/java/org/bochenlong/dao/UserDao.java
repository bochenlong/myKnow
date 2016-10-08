package org.bochenlong.dao;

import org.bochenlong.config.DataConfig;
import org.midao.jdbc.core.MjdbcFactory;
import org.midao.jdbc.core.handlers.input.named.MapInputHandler;
import org.midao.jdbc.core.service.QueryRunnerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Created by bochenlong on 16-9-22.
 */
public class UserDao {
    private QueryRunnerService runner;
    private Connection conn;

    public UserDao() {
        conn = DataConfig.getConnection();
        this.runner = MjdbcFactory.getQueryRunner(conn);
    }

    public void save(String name, String password) {
        Map<String, Object> map = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        map.put("id", uuid);
        map.put("name", name);
        map.put("password", password);
        map.put("createTime", new Timestamp(System.currentTimeMillis()));
        StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
        stringJoiner.add(":id").add(":name").add(":password")
                .add(":createTime");
        MapInputHandler input = new MapInputHandler("insert into user_" + partition(uuid) + " values " + stringJoiner, map);

        try {
            runner.update(input);
            runner.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public String partition(String uuid) {
        return uuid.hashCode() % 2 == 0 ? "01" : "02";
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
