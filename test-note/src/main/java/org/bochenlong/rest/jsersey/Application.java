package org.bochenlong.rest.jsersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Created by bochenlong on 16-10-8.
 */
public class Application {
    public static final String BASE_URI = "http://localhost:8080/";
    private static String SERVER_HOST = "localhost";
    private static int SERVER_PORT = 8989;


    public static HttpServer startServer(String host, int port) {
        final ResourceConfig rc = new ResourceConfig().packages("org.bochenlong.rest.jersey");
        URI baseUri = UriBuilder.fromUri(BASE_URI).host(host).port(port).build();
        return GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer(SERVER_HOST, SERVER_PORT);
    }
}
