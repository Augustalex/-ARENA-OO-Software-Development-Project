/*
 * To change this license getHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cipher;
import boardGameLibrary.boardGame.move.PlayerAction;
/**
 *
 * @author S132063
 */
public class MessageDecodePlayerAction extends MessageParser {
    private int y;
    private int x;
    
    public PlayerAction DecodePlayerActionCoordinates(String toBeDecoded){
       
        this.x = getIntegerVariable(toBeDecoded, "X");
        this.y = getIntegerVariable(toBeDecoded, "Y");
        PlayerAction action = new PlayerAction(this.y, this.x);
        return action;
    }
  
    public int getXCoordinates(){
        return this.x;
    }
    public int getYCoordinates(){
        return this.y;
    }
}
