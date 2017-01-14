package org.bochenlong.rest.vert.hander;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by bochenlong on 16-9-21.
 */
public class RouterRegister {
    public static void register(Router router) {
        router.route().handler(BodyHandler.create());

        router.get("/")
                .produces("text/plain")
                .handler(context -> context.response().end("hello world"));

    }
}
