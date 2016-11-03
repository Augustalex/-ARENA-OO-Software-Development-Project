/*
 * To change this license getHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cipher;

/**
 *
 * @author S132063
 */
public class MessageParser {
    
    public void parser(String message){
        System.out.println(getHeader(message));
    }
    public int getIntegerVariable(String message, String identifier){        
        StringBuilder builder = new StringBuilder();
        
        int i = message.indexOf(identifier) + 1;
        while(!Character.isAlphabetic(message.charAt(i)) && i < message.length()){
            builder.append(message.charAt(i++));
        }
        return Integer.parseInt(builder.toString());
    }
    public static String getHeader(String message){
        StringBuilder builder = new StringBuilder();
        
        int i = 0;
        while(Character.isAlphabetic(message.charAt(i))){
            builder.append(message.charAt(i++));
        }
        return builder.toString();
    }
}
