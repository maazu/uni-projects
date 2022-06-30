package Server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Server  extends JFrame {
    static JTextArea serverStatusWindow;
    private ServerSocket server;
    private boolean running =true;
    private int counter;
    ArrayList<ServerHandler> connections = new ArrayList<ServerHandler>();
    Set<Integer> onlinePlayers =  new HashSet<>();
    int ballRecord;

    public static void main(String[] args){
        new Server();
    }

    public Server(){
        super("Reg No: 1706038 CE303 Assignment Server");
        serverStatusWindow = new JTextArea();
        add(new JScrollPane(serverStatusWindow));
        serverStatusWindow.setEditable(false);
        setSize(350, 200); //Sets the window size
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            server = new ServerSocket(7088);
            serverStatusWindow.append("Game Server Started at "+ server.getInetAddress().getHostName()+" on port "+server.getLocalPort());
            ServerHandler.ServerStatusWindow("Waiting for players  to connect.....");

            counter = 0;
            while (running) {
                counter++;
                Socket connection = server.accept();
                ServerHandler.ServerStatusWindow("New Player  " + counter +" has joined the game ");
                ServerHandler playerthread = new ServerHandler(connection,this,counter);

                playerthread.start();
                connections.add(playerthread);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }

    }
}

