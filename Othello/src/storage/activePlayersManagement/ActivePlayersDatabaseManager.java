package storage.activePlayersManagement;

import boardGameLibrary.players.Player;
import javafx.scene.paint.Color;
import storage.databaseManagement.DatabaseManager;
import storage.exceptions.NotConnectedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-10-27.
 */
public class ActivePlayersDatabaseManager {

    public final static String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
            "databaseName=oomuht1603;user=oomuht1603; password=bagg66";

    public final static String tableName = "ActivePlayers";
    public final static String[] tableColumnNames = {
            "playerName",
            "ipv4",
            "color"
    };

    public final static String uniqueIdentifierColumnName = ActivePlayersDatabaseManager.tableColumnNames[0];

    private DatabaseManager databaseManager;

    public ActivePlayersDatabaseManager() throws SQLException {
        this.databaseManager = new DatabaseManager(ActivePlayersManager.connectionUrl);
        this.createActivePlayersTable();
    }

    public void addActivePlayer(Player player, String ipv4) throws SQLException {

        String sqlTableIdentifier = ActivePlayersDatabaseManager.tableName + getColumnNamesTuple();

        String[] values = new String[]{
                player.getName(),
                ipv4,
                ActivePlayersDatabaseManager.toRGBCode(player.getColor())
        };

        this.databaseManager.insertInto(sqlTableIdentifier, values);
        System.out.println("Player Added to the DB..");
    }

    public void removeActivePlayer(Player player) throws SQLException {
        String values = "\'" + player.getName() + "\'";
        this.databaseManager.runStatement(
                "Delete FROM " +
                ActivePlayersDatabaseManager.tableName +
                " where " +
                ActivePlayersDatabaseManager.uniqueIdentifierColumnName +
                " like " +
                values
        );

        System.out.println("Player Removed from the DB..");
    }

    public String[][] getActivePlayerRows() throws NotConnectedException, SQLException {
        ResultSet resultSet = this.databaseManager.getTableData(ActivePlayersDatabaseManager.tableName);
        int columnCount = resultSet.getMetaData().getColumnCount();

        List<String[]> rows = new ArrayList<>();

        while(resultSet.next()) {
            String[] columns = new String[columnCount];
            for(int i = 0; i < columnCount; i++)
                columns[i] = resultSet.getObject(0).toString();

            rows.add(columns);
        }

        return rows.stream().toArray(String[][]::new);
    }

    private void createActivePlayersTable() throws SQLException {
        this.databaseManager.createTable(
                ActivePlayersDatabaseManager.tableName,
                "create table ActivePlayers( \n" +
                "playerName varchar(40) not null Primary key,\n" +
                "ipv4 varchar(30) NOT NULL,\n" +
                "color varchar(30) NOT NULL,\n" +
                ")"
        );

        System.out.println("Database Tables have been created");
    }

    private String getColumnNamesTuple(){
        String tuple = "(";
        for(String columnName : ActivePlayersDatabaseManager.tableColumnNames)
            tuple += columnName;
        tuple += ")";

        return tuple;
    }

    private static String toRGBCode(Color color ) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
