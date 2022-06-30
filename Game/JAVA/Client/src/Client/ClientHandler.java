package Client;

import javax.imageio.IIOException;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket connection;
    private DataInputStream input;
    private static DataOutputStream output ;
    private boolean running = true;
    public ClientHandler(Socket connection,Client client){
        this.connection =connection;

    }
    public void run() {
        try {
            input = new DataInputStream(connection.getInputStream());
            output = new DataOutputStream(connection.getOutputStream());
            while (running) {
                try{
                    //here client get the msg from server
                    String serverResponse = input.readUTF();
                    playerStatusWindow(serverResponse);
                }
                catch (IIOException e){
                    e.printStackTrace();
                }
            } //end of the while loop
        } catch (IOException e){
            System.err.println("Disconected From the Server, the program has been closed.");

        }
    }


    public static void sendRequestToServer(String playerRequest){
        try {
            output.writeUTF(playerRequest);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playerStatusWindow(final String text){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        Client.playerStatusWindow.append("\n"+text);
                    }
                }
        );
    }
    public void closeGame(){
        try {
            connection.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
