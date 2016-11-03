/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.sql.*;

/**
 *
 * @author S132063
 */
public class DatabaseManagerOLDOLDOLD  {
    
    String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
			"databaseName=oomuht1603;user=oomuht1603; password=bagg66";
    // Declare the JDBC objects.
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    DatabaseMetaData dbMetaData = null;
    Boolean success = false;
    
    
    public void initateDatabas(){
        try {
            // Establish the connection.
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to DB");
            dbMetaData =  con.getMetaData();
            rs = dbMetaData.getTables("oomuht1603", "dbo", "%", new String[] {"TABLE"});
            while (rs.next()) {
                    //System.out.println(rs.getString(3));
                    if( rs.getString(3).equals("ActivePlayers")){
                        success = true;
                        rs.close();
                        break;
                    }
            }
            stmt = con.createStatement();
            if(!success){
                createTables();
                insertPlayer(1,"skynet","192.168.20.1:22222", "hej");
                success = true;        
            }
            if (success){
                rs = getPlayerData();
                printResult(rs);
            }                  
        }     
        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
    public ResultSet getPlayerData() throws SQLException{
        String SQL = " select * from ActivePlayers";
        rs = stmt.executeQuery(SQL);
        rs.close();
        return rs;
    }
    public void printResult(ResultSet rs) throws SQLException{
        while (rs.next()) {
               System.out.println("Player ID: " + rs.getString(1) + " PlayerName: " + rs.getString(2)+ " IPV4: "+rs.getString(3)+ " Color: " + rs.getString(4));
            }
    }
    public void insertPlayer(int playerID, String playerName, String ipv4, String color) throws SQLException{
        String values = "values(" + playerID +",'" +playerName + "','"+ ipv4 +"','"+ color +"')";
        stmt.addBatch("insert into ActivePlayers(playerID,playerName, ipv4, color)\n" + values);
        stmt.executeBatch();
        System.out.println("Player Added to the DB..");
    }
    public void createTables() throws SQLException{
        stmt = con.createStatement();
        stmt.addBatch("create table ActivePlayers(\n" +
                      "playerID int Primary key,\n" +
                      "playerName varchar(40) not null unique,\n" +
                      "ipv4 varchar(30) NOT NULL,\n" +
                      "color varchar(30) NOT NULL, \n" +
                      ")"
                        );
        stmt.executeBatch();
        System.out.println("Database Tabels have been created");
    }
    
}
