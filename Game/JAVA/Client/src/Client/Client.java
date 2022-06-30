package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;



public class Client extends JFrame {
    private ClientHandler ch;
    private JLabel label;
    static JTextArea playerStatusWindow;
    static JTextField playerTextField;
    static JButton who,pass,onlineplayers,exit;

    public static void main(String[] args) throws IOException {
        new Client();
    }

    public Client(){
        super("Reg No: 1706038 CE303 Assignment Client ");
        label =new JLabel("Enter the Player ID:");
        playerTextField = new JTextField(12);
        playerStatusWindow = new JTextArea();
        playerStatusWindow.setEditable(false);
        JPanel topPanel = new JPanel();
        topPanel.setLayout( new GridBagLayout() );
        topPanel.add(label);
        topPanel.add(playerTextField);
        add(topPanel,BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout( new GridBagLayout() );
        who = new JButton("WHO");
        pass = new JButton("PASS");
        onlineplayers = new JButton("Online");
        exit = new JButton("Exit");
        bottomPanel.add(who);
        bottomPanel.add(pass);
        bottomPanel.add(onlineplayers);
        bottomPanel.add(exit);
        add(bottomPanel,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JScrollPane(playerStatusWindow));
        setSize(350, 200); //Sets the window size
        setVisible(true);
        pass.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String passball = playerTextField.getText();
                        passball =  passball.replaceAll("[^\\d.]", "");
                        passball=passball.trim();
                        System.out.println(passball);
                        ClientHandler.sendRequestToServer(passball);

                    }
                }
        );

        who.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        ClientHandler.sendRequestToServer("WHO");

                    }
                }
        );
        exit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        ClientHandler.sendRequestToServer("Exit");
                        playerStatusWindow.append("\nYour Socket has bas closed, you can now close the program. ");
                    }
                }
        );


        playerTextField.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){

                        ClientHandler.sendRequestToServer(event.getActionCommand());
                        playerTextField.getText();
                    }
                }
        );
        onlineplayers.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        ClientHandler.sendRequestToServer("ONLINE PLAYERS");
                    }
                }
        );


       addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                if(ch!=null)ch.closeGame();
            }
        });



        // start connection
        try {
            Socket connection =  new Socket("localhost",7088);
            playerStatusWindow.append("Game Server Started at "+connection.getInetAddress().getHostName());

            ch = new ClientHandler(connection,this);
            ch.start();


        }
        catch (ConnectException e){
           playerStatusWindow.append("Game server Unavaliable !");
        }
        catch (IOException e){
             e.printStackTrace();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}