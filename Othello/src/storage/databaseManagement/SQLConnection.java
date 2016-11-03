package storage.databaseManagement;

import storage.exceptions.NotConnectedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by August on 2016-10-27.
 */
public class SQLConnection {

    private final String connectionUrl;
    private Connection connection = null;
    private Statement statement = null;

    public boolean isConnected = false;

    public SQLConnection(String connectionUrl) throws SQLException {
        this.connectionUrl = connectionUrl;
        this.connect();
    }

    public SQLConnection connect() throws SQLException{
        try{
            this.connection = DriverManager.getConnection(this.connectionUrl);
            this.isConnected = true;
        }
        catch(Exception e){
            this.isConnected = false;
            throw e;
        }

        System.out.println("Connected to DB");
        return this;
    }

    public void close(){
        try{

            if(this.statement != null)
                this.statement.close();

            if(this.connection != null)
                this.connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public boolean isConnected(){
        return this.isConnected;
    }

    public SQLConnection ifConnected() throws NotConnectedException{
        if(this.isConnected)
            return this;
        else
            throw new NotConnectedException();
    }

    public Statement getStatement() throws SQLException {
        this.statement = this.connection.createStatement();
        return this.statement;
    }
}
