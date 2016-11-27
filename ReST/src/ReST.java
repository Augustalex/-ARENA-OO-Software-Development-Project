import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Methods for completely handling every ReST API Use Case.
 */
public abstract class ReST implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExchange){
        try {
            switch (httpExchange.getRequestMethod()) {
                case "GET":
                    this.onGet(httpExchange);
                    break;
                case "POST":
                    this.onPost(httpExchange);
                    break;
                case "PUT":
                    this.onPut(httpExchange);
                    break;
                case "DELETE":
                    this.onDelete(httpExchange);
                    break;
                default:
                    this.onGet(httpExchange);
            }
        }
        catch(Exception e){
            System.out.println("Error in http handler for context: " + httpExchange.getHttpContext().getPath());
            e.printStackTrace();
        }
    }

    public abstract void onGet(HttpExchange httpExchange) throws Exception;

    public abstract void onPost(HttpExchange httpExchange) throws Exception;

    public abstract void onDelete(HttpExchange httpExchange) throws Exception;

    public abstract void onPut(HttpExchange httpExchange) throws Exception;

}
