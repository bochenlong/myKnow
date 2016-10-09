package org.bochenlong.vert;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.bochenlong.vert.hander.RouterRegister;

/**
 * Created by bochenlong on 16-9-21.
 */
public class Application extends AbstractVerticle {
    private static int PROCESSOR_NUM = Runtime.getRuntime().availableProcessors();
    private static String SERVER_HOST = "localhost";
    private static int SERVER_PORT = 8888;

    @Override
    public void start() throws Exception {
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
        server.requestHandler(router::accept).listen(SERVER_PORT, SERVER_HOST);

        System.out.println(this.deploymentID() + " is deployed");

    }

    public static void main(String[] args) {
        System.out.println("server is ready to start");

        Vertx vertx = Vertx.vertx(new VertxOptions().setBlockedThreadCheckInterval(300000L));
        vertx.deployVerticle(Application.class.getName(),
                new DeploymentOptions().setInstances(2 * PROCESSOR_NUM), e -> {
                    if (e.succeeded()) {
                        System.out.println("all verticle is deployed");
                    } else {
                        e.cause().printStackTrace();
                    }
                });
    }
}
