import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

/**
 * Created by August on 2016-11-26.
 */
public class ReSTContainer extends HttpServer {

    private ReST api;
    private HttpServer server;

    public ReSTContainer(ReST api) throws IOException {
        this.api = api;

        this.server = HttpServer.create(new InetSocketAddress("0.0.0.0", 1339), 0);
        System.out.println(server.getAddress());
        server.setExecutor(null);

    }

    @Override
    public void bind(InetSocketAddress inetSocketAddress, int i) throws IOException {
        server.bind(inetSocketAddress, i);
    }

    public void start()  {
        //server.createContext("/users", api);
        server.start();
    }

    @Override
    public void setExecutor(Executor executor) {
        server.setExecutor(executor);
    }

    @Override
    public Executor getExecutor() {
        return server.getExecutor();
    }

    @Override
    public void stop(int i) {
        server.stop(i);
    }

    @Override
    public HttpContext createContext(String s, HttpHandler httpHandler) {
        return server.createContext(s, httpHandler);
    }

    @Override
    public HttpContext createContext(String s) {
        return this.createContext(s);
    }

    @Override
    public void removeContext(String s) throws IllegalArgumentException {
        this.removeContext(s);
    }

    @Override
    public void removeContext(HttpContext httpContext) {
        this.removeContext(httpContext);
    }

    @Override
    public InetSocketAddress getAddress() {
        return this.getAddress();
    }

    public ReST getApi(){
        return this.api;
    }
}
