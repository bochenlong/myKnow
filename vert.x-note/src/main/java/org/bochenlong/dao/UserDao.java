package org.bochenlong.dao;

import org.bochenlong.config.DataConfig;
import org.midao.jdbc.core.MjdbcFactory;
import org.midao.jdbc.core.handlers.input.named.MapInputHandler;
import org.midao.jdbc.core.service.QueryRunnerService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by bochenlong on 16-9-22.
 */
public class UserDao {
    private QueryRunnerService runner;

    public UserDao() {
        this.runner = MjdbcFactory.getQueryRunner(DataConfig.getConnection());
    }

    public void save(String name,String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        map.put("createTime", new Timestamp(System.currentTimeMillis()));
        StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
        stringJoiner.add(":name").add(":password")
                .add(":createTime");
        MapInputHandler input = new MapInputHandler("insert into user_ values " + stringJoiner, map);

        try {
            runner.update(input);
            runner.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
