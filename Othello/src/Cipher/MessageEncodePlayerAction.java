/*
 * To change this license getHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cipher;

// import boardGameLibrary.boardGame.move.PlayerAction;

/**
 *
 * @author S132063
 */
public class MessageEncodePlayerAction extends MessageParser {
    private String encodedAction = "";
    public MessageEncodePlayerAction(int y, int x){
        this.encodedAction ="PlayerAction ";
        this.encodedAction = this.encodedAction.concat("X" + Integer.toString(x)+ "Y" + Integer.toString(y) + "EndofMessage");
    }
    public String getEncodedAction(){
        return this.encodedAction;
    }
}



