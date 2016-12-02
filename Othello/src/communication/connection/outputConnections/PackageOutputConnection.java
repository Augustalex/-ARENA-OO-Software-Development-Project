package communication.connection.outputConnections;

import communication.connection.inputConnections.SocketConnection;
import communication.sender.Package;
import communication.sender.PackageCompiler;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Extends the SocketConnection that provides an interface for connecting
 * to a socket that is able to transmit the data.
 *
 * Implements the OutputConnection that requires the transmit method.
 * This allows the class to transmit Packages over the Socket connection.
 */
public class PackageOutputConnection extends SocketConnection implements OutputConnection<Package> {

    public PackageOutputConnection(int port) throws IOException {
        super(port);
    }

    @Override
    public void transmit(Package object) throws IOException {
        PrintStream printStream = new PrintStream(this.connection.getOutputStream());
        printStream.print(PackageCompiler.encode(object));
        printStream.flush();
    }
}
