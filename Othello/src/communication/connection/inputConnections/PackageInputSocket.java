package communication.connection.inputConnections;

import communication.receiver.exceptions.InvalidPackageException;
import communication.sender.Package;
import communication.sender.PackageCompiler;
import communication.sender.PackageValidator;

import java.io.IOException;
import java.util.Scanner;

/**
 * Extends the SocketConnection which implements methods
 * for creating a socket that listens to a port.
 *
 * This class then implements the interface for Receiving, in this
 * case, a {@link Package}.
 */
public class PackageInputSocket extends SocketConnection implements InputConnection<Package>{

    public PackageInputSocket(int port) throws IOException {
        super(port);
    }

    @Override
    public Package receive() throws Exception {
        Scanner scanner = new Scanner(this.connection.getInputStream());
        String message = scanner.next();

        if(!PackageValidator.validatePackage(message))
            throw new InvalidPackageException();
        else
            return PackageCompiler.decode(message);
    }
}
