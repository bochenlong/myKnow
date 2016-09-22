package org.bochenlong.hander;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.RoutingContext;
import org.bochenlong.dao.UserDao;

/**
 * Created by bochenlong on 16-9-21.
 */
public class UserHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext context) {
        String name = context.request().getParam("name");
        String password = context.request().getParam("password");
        save(name, password);
        context.response().end("ok");
    }

    private void save(String name, String password) {
        UserDao userDao = new UserDao();
        userDao.save(name, password);
    }
}
