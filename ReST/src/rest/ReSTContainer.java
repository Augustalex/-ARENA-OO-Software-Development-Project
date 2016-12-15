package rest;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;

/**
 * Created by August on 2016-11-26.
 */
public abstract class ReSTContainer extends HttpServer {

    private HttpServer server;

    public ReSTContainer(int port) {
        try {
            this.server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
            System.out.println(server.getAddress());
            server.setExecutor(null);
        }
        catch(IOException ex){
            System.out.println("Could not start Service Container at " + port);
            ex.printStackTrace();
        }

    }

    public int getPort(){
        return this.getAddress().getPort();
    }

    @Override
    public void bind(InetSocketAddress inetSocketAddress, int i) throws IOException {
        server.bind(inetSocketAddress, i);
    }

    public void start()  {
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
        return server.createContext(s);
    }

    @Override
    public void removeContext(String s) throws IllegalArgumentException {
        server.removeContext(s);
    }

    @Override
    public void removeContext(HttpContext httpContext) {
        server.removeContext(httpContext);
    }

    @Override
    public InetSocketAddress getAddress() {
        return server.getAddress();
    }

    public String getLocalAddress() throws UnknownHostException {
        return getAddress().getAddress().getLocalHost().getHostAddress();
    }
}
