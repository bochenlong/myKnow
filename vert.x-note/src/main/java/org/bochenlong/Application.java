package org.bochenlong;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.bochenlong.hander.RouterRegister;

/**
 * Created by bochenlong on 16-9-21.
 */
public class Application {
    public static void main(String[] args) {
        // vert.x 配置
        Vertx vertx = Vertx.vertx(new VertxOptions()
                .setWorkerPoolSize(1000)
        );

        // 初始化服务器
        HttpServer server = vertx.createHttpServer();

        // 声明路由
        Router router = Router.router(vertx);

        // 静态资源设置
        router.route("/static/*").handler(
                StaticHandler.create("static")
                        .setIndexPage("index.html")// 静态路径会被映射得到static下的资源
        );

        // 注册全部路由
        RouterRegister.register(router);

        // 开启服务器
        server.requestHandler(router::accept).listen(8989, "localhost");
    }


}
