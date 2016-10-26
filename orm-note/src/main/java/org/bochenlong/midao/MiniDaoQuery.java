package org.bochenlong.midao;

import org.bochenlong.datasource.DataConfig;
import org.midao.jdbc.core.MjdbcFactory;
import org.midao.jdbc.core.handlers.input.InputHandler;
import org.midao.jdbc.core.handlers.input.query.QueryInputHandler;
import org.midao.jdbc.core.handlers.output.MapListOutputHandler;
import org.midao.jdbc.core.service.QueryRunnerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by bochenlong on 2016/9/16.
 */
public class MiniDaoQuery {

    public static void main(String[] args) throws SQLException {
        queryDemosWithSetProperties();
    }

    /**
     * 查询Demo含set属性
     *
     * @throws SQLException
     */
    private static void queryDemosWithSetProperties() throws SQLException {
        QueryRunnerService runner = MjdbcFactory.getQueryRunner(DataConfig.getConnection());
        // 关联查询表
        String sql = "select d.*,a.* from user d inner join address a on d.id = a.id";
        InputHandler inputHandler = new QueryInputHandler(sql, null);
        // 查询出全部属性的map
        List<Map<String, Object>> list = runner.query(inputHandler, new MapListOutputHandler());
        // *****
        // 根据map封装成Object
        // *****
        System.out.println(list.size());
    }
}
