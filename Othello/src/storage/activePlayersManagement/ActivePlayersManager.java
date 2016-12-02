package storage.activePlayersManagement;

import boardGameLibrary.players.Player;
import boardGameLibrary.players.RemotePlayer;
import javafx.scene.paint.Color;
import storage.IActivePlayerManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-10-27.
 */
public abstract class ActivePlayersManager implements IActivePlayerManagement{

    public final static String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
            "databaseName=oomuht1603;user=oomuht1603; password=bagg66";

    private ActivePlayersDatabaseManager playerDatabaseManager;

    public ActivePlayersManager() {
        try {
            this.playerDatabaseManager = new ActivePlayersDatabaseManager();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not instantiate class.");
        }
    }

    @Override
    public void addActivePlayer(Player player) {
        try {
            this.playerDatabaseManager.addActivePlayer(player, "192.168.1.2");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add player.");
        }
    }

    @Override
    public void removeActivePlayer(Player player) {
        try {
            this.playerDatabaseManager.removeActivePlayer(player);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not remove player.");
        }
    }

    @Override
    public Player[] getActivePlayers(){
        try {
            List<Player> players = new ArrayList<>();

            String[][] rows = this.playerDatabaseManager.getActivePlayerRows();

            for(String[] row : rows)
                players.add(createPlayerFromRow(row));

            return players.stream().toArray(Player[]::new);

        } catch (Exception e){
            e.printStackTrace();
            return new Player[0];
        }
    }

    private Player createPlayerFromRow(Object[] row){
        if(row.length < 3)
            throw new IllegalArgumentException();
        else
            try {
                return new RemotePlayer((String) row[0], Color.web((String) row[2])).setIP((String) row[1]);
            }
            catch(Exception e){
                System.out.println("Illegal columns in row.");
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
    }
}
