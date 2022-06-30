package Server;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

// Player handler
public class ServerHandler extends Thread {
    private static final Object lock = new Object();
    private int playerID;
    private static Server server;
    private Socket  connection;
    private DataInputStream input;
    private DataOutputStream output ;
    private boolean running = true;

    public ServerHandler(Socket connection, Server server, int playerID){
        super("Server Connection Thread");
        this.connection = connection;
        this.server = server;
        this.playerID = playerID;
    }

    public void run(){
        try {
            addID(playerID);
            input = new DataInputStream(connection.getInputStream());
            output = new DataOutputStream(connection.getOutputStream());
            sendRequestToClient("Your Player ID Is "+ String.valueOf(playerID));
            sendRequestToClient("You can now pass the ball");
            String clientMessage;

            // pass the ball to the very first player
            if(server.onlinePlayers.size()==1){
                server.ballRecord = playerID;
                sendRequestToClient("You have the ball now !");
            }

            while(running){
                clientMessage = input.readUTF();
                if(clientMessage.equals("WHO")){
                    whoHasTheBall();
                }
                if(clientMessage.equals("ONLINE PLAYERS")){
                    printOnlinePlayers();
                }

                if(clientMessage.matches("\\d+")){
                    passBall(playerID,clientMessage);
                }
            }
        } //end of try statement
        catch (IOException e) {
            System.out.println(e);
        } finally {
            // player left the Game
            leaveGame();
        }
    }

    private void addID(int id) {
        synchronized (lock) {
            server.onlinePlayers.add(id);
        }
    }

    private void leaveGame(){
        synchronized (lock){
            //Player left message
            String playerleft = "Player ID " + playerID + " has left the game. ";
            ServerStatusWindow(playerleft);
            server.onlinePlayers.remove(playerID);
            server.connections.remove(this);
            if (playerID == server.ballRecord) {
                server.ballRecord = getRandomPlayer();
            }
            System.out.println("Online Players: "+server.onlinePlayers.size());
            closeConnection();
        }
    }

    // sends the message to only client who sending message
    private void sendRequestToClient(String request){
        try {
            output.writeUTF(request);
            output.flush();
        } catch (IOException e) {
            System.err.println("Client closed connection");
            System.err.println(e.getMessage());
        }

    }

    // BroadCast the message to All Clients
    public static void sendRequestToAllClients(String request){
        synchronized (lock){
            for(int index=0;index < server.connections.size();index++){
                ServerHandler sc = server.connections.get(index);
                sc.sendRequestToClient(request);
                System.out.println(sc);
            }
        }
    }

    /* ------------------------------------------------
            ----------GAME HANDLING ----------
                      LOGIC METHODS
    /* ------------------------------------------------*/

    // checks which player have the ball
    private void whoHasTheBall(){
        synchronized (lock){
            sendRequestToClient("Payer ID : "+ Integer.valueOf(server.ballRecord) + " has the ball . ");
        }
    }

    public int getRandomPlayer() {
        Iterator iterator = server.onlinePlayers.iterator();
        if(iterator.hasNext()){
            return ((Integer) iterator.next());
        }
        return -1;
    }


    // Pass the to other player if exist, error if playe trying to pass the to himself

    private  void passBall(int playerID,String to){
        synchronized (lock){
            if (server.ballRecord == playerID){
                if(server.onlinePlayers.contains(Integer.valueOf(to))){
                    server.ballRecord = Integer.valueOf(to);
                    sendRequestToAllClients("Player ID : "+playerID+"has passed the ball to Player ID: "+to);
                }
                else{
                    sendRequestToClient("Player unavailable / Offline.");
                }
            }
            else{
                sendRequestToClient("You can not pass the ball because you do not have a ball.");
            }
         }
    }


    // print the list of all the online currently in the game

    private void printOnlinePlayers(){
        synchronized (lock) {
            sendRequestToClient("ONLINE PLAYERS :");
            for (Integer s : server.onlinePlayers) {
                sendRequestToClient("Player ID: " + s.toString());
            }
        }
    }



    public void closeConnection(){
        try{
            output.close(); //Closes the output path to the client
            input.close(); //Closes the input path to the server, from the client.
            connection.close(); //Closes the connection between you can the client
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static void ServerStatusWindow(final String text){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        server.serverStatusWindow.append("\n"+text);
                    }
                }
        );
    }
}
