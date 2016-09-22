package org.bochenlong;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.impl.launcher.commands.RunCommand;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.bochenlong.hander.RouterRegister;

/**
 * Created by bochenlong on 16-9-21.
 */
public class Application extends AbstractVerticle {

    public static void main(String[] args) {
        // vert.x 配置
        Vertx vertx = Vertx.vertx(new VertxOptions()
                .setWorkerPoolSize(1000)
        );

        vertx.deployVerticle(Application.class.getName(),
                new DeploymentOptions().setInstances(Runtime.getRuntime().availableProcessors()));

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

        System.out.println("server start at localhost:8989");
    }

}
