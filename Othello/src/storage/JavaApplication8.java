/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S132063
 */
public class JavaApplication8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    DatabaseManagerOLDOLDOLD db = new DatabaseManagerOLDOLDOLD();
        try {
            db.initateDatabas();
            
            ResultSet rs = db.getPlayerData();
            db.insertPlayer(4, "Mange", "Okan", "TRUE");
            rs = db.getPlayerData();
            db.printResult(rs);
         
            
        } catch (SQLException ex) {
            Logger.getLogger(JavaApplication8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
