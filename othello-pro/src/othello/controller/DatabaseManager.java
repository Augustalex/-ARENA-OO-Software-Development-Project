/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.controller;

import java.sql.*;

/**
 * The DatabaseManager sets up a connection to a SQL database. 
 * It requests port-number, and IP-address to a remote server by a
 * CallableStatement, a predefined procedure in the SQL database. 
 * @author Simon
 */

public class DatabaseManager {
    
    private static final int GROUP_ID = 5;
    private String hostName;
    private int portName;
    private CallableStatement callableStatement;
    private String callString;
    /**
     * Constructor for the DatabaseManager.
     * Calls the getConnectionDetails() method. 
     */
    public DatabaseManager(){
        getConnectionDetails();
    }
    /**
     * Method for getting a IP address to the server. 
     * @return String hostName
     */
    public String getHostName(){    
        return hostName;            
    }
    /**
     * Method for getting the port-number to the server.
     * @return int portName
     */
    public int getPortName(){    
        return portName;            
    }     
    /**
     * Method for getting connection details to the game server. 
     * This method calls a predefined procedure in the SQL database which returns
     * the IP-address and port-number from the database. 
     * Throws a SQLException if something goes wrong with the connection.
     */
    private void getConnectionDetails(){
        callString = "{call dbo.GetConnectionDetails(?,?,?)}";
        
        Connection connection = getDatabaseConnection();
        System.out.println("Getting connection details for server...");
        
        try{
            callableStatement = connection.prepareCall(callString);
            callableStatement.setInt(1, GROUP_ID);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR); //adress
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER); //port
            
            callableStatement.execute();
            
            ResultSet resultSet = callableStatement.getResultSet();
            hostName = callableStatement.getString(2);
            portName = callableStatement.getInt(3);            
            
        }catch(SQLException ex){
            System.out.println("Something went wrong while getting data from server...");
            ex.printStackTrace();
        }
        finally{
            System.out.println("hostname: " + hostName);
            System.out.println("port: " + portName);
            if(callableStatement != null){
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    /**
     * Method for setting up a connection to a SQL database through JDBC.
     * Returns a connection object which can be used by other methods. 
     * @return Connection connection
     */
    private static Connection getDatabaseConnection(){

        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1605;user=oomuht1605;password=boss66");
            System.out.println("Database connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }
    
  
}
