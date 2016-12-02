package communication.connection.inputConnections;

import communication.requests.Request;
import communication.requests.RequestCompiler;
import communication.sender.Package;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Composes the {@link PackageInputSocket} to return a List of {@link Request}s
 * instead of {@link Package}s.
 */
public class RequestInputSocket implements InputConnection<List<Request>> {

    private PackageInputSocket packageSocket;

    public RequestInputSocket(int port) throws IOException {
        this.packageSocket = new PackageInputSocket(port);

    }

    @Override
    public void connect() throws Exception {
        this.packageSocket.connect();
    }

    @Override
    public List<Request> receive() throws Exception {
        Package payload = this.packageSocket.receive();

        return(
                Stream
                .of(payload.getRequests())
                .map(RequestCompiler::decode)
                .collect(Collectors.toList())
        );
    }

    @Override
    public void close() {
        this.packageSocket.close();
    }
}
