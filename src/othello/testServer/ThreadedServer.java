/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S132063
 */
public class ThreadedServer implements Runnable {
    protected int serverport = 4444;
    protected ServerSocket serverSocket = null;
    protected Thread  runningThread = null;
    protected boolean isStopped = false;
    
    public ThreadedServer(){
    }
    public ThreadedServer(int port){
    this.serverport = port;
    }
    
    @Override
    public void run() {
        synchronized(this){
        this.runningThread = Thread.currentThread();
    }
    openServerSocket();
    while(!isStopped){
        Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped){
                    System.out.println("Server Stopped.");
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            new Thread(new ClientWorker(clientSocket)).start();
            
    }
    System.out.println("Server Stopped");
 
    }
    
    private void openServerSocket(){
        try {
            this.serverSocket = new ServerSocket(this.serverport);
        } catch (IOException e) {
           throw new RuntimeException("Cannot open port" + this.serverport, e);
        }
    }
    private synchronized boolean isStopped() {
        return this.isStopped;
    }
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
}
