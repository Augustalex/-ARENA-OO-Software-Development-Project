/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;

/**
 *
 * @author S132063
 */
public abstract class ClientWorker implements Runnable {
    private Socket client;
    
    ClientWorker(Socket client){
    this.client = client;
    }
   
}
