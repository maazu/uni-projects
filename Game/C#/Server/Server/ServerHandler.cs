using System;
using System.IO;
using System.Linq;
using System.Net.Sockets;

namespace Server
{
    public class ServerHandler
    {
        private static readonly object _lock = new Object();
        private static  TcpListener server;
        private int playerId;
        private TcpClient _connection;
        private Stream stream;
        private StreamWriter _writer;
        private StreamReader _reader;
        private bool running;
        public ServerHandler(TcpClient connection, TcpListener server, int playerId)
        {
            ServerHandler.server = server;
            this._connection = connection;
            this.playerId = playerId;
        }


        public void run()
        {
            try
            {
                AddID(playerId);
                stream = _connection.GetStream();
                _reader = new StreamReader(stream);
                _writer = new StreamWriter(stream);
                sendRequestToClient("Your player ID is " + playerId);
                sendRequestToClient("You can now pass the ball");
                String ClientMessage = "";

                if (Server.onlinePlayers.Count == 1)
                {
                    Server.ballRecord = playerId;
                    sendRequestToClient("You have the ball now ");
                }

                while (true)
                {
                    string clientMessage = _reader.ReadLine();
                    clientMessage.Trim();
                    if (clientMessage.Equals("WHO"))
                    {
                        whoHasTheBall();
                    }

                    if (clientMessage.Equals( "ONLINE PLAYERS"))
                    {
                        printOnlinePlayers();
                    }

                    String[] Words = clientMessage.Split(' ');
                    
                    if (clientMessage.Equals( "PASS"))
                    {
                        printOnlinePlayers();
                    }
                }
            }
            catch (IOException e)
            {
                Console.WriteLine(e);
            }
            finally
            {
                leaveGame();
            }
        }  // end of the run method 



        private void AddID(int id)
        { 
            lock (_lock)
            {
                Server.onlinePlayers.Add(id);
            }
        }


        private void leaveGame()
        {
            lock (_lock)
            {
               String playerLeft = "Player ID " + playerId + " has left the game. ";
               Console.WriteLine(playerLeft);
               Server.onlinePlayers.Remove(playerId);
               Server.connections.Remove(this);
               if (playerId == Server.ballRecord)
               {
                   Server.ballRecord = getRandomPlayer();
               }
               Console.WriteLine("Online players"+ Server.onlinePlayers.Count);
               closeConnection();
            } 
        }
        private void sendRequestToClient(String request){
            try {
                _writer.Write(request);
                _writer.Flush();
            } catch (IOException e) {
                Console.WriteLine("Client closed connection");
                Console.WriteLine(e);
            }

        }

        private void sendRequestToAllClients(String request)
        {
            lock (_lock)
            {
                for (int index = 0; index < Server.connections.Count; index++)
                {
                    ServerHandler sc = Server.connections[index];
                    sc.sendRequestToClient(request);
                    Console.WriteLine(sc);
                }
            }
        }
        
        /* ------------------------------------------------
               ----------GAME HANDLING ----------
                         LOGIC METHODS
        /* ------------------------------------------------*/

        // checks which player have the ball
        private void whoHasTheBall(){
           lock (_lock){
                sendRequestToClient("Payer ID : "+Server.ballRecord + " has the ball."+"/n");
           }
        }

        public int getRandomPlayer()
        {
            int anyPlayerId = (Server.onlinePlayers.Count>0)?Server.onlinePlayers.Single():-1;
            return anyPlayerId;
        }
        
        // Pass the to other player if exist, error if playe trying to pass the to himself
        private  void passBall(int playerID,String to){
           lock (_lock){
                if (Server.ballRecord == playerID){
                    if(Server.onlinePlayers.Contains(int.Parse(to))){
                        Server.ballRecord = int.Parse(to);
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
           lock (_lock) {
                sendRequestToClient("ONLINE PLAYERS :");
                foreach (int s in Server.onlinePlayers) {
                    sendRequestToClient("Player ID: " + s);
                } 
           }
        }


        //Closes the connection   
        
        public void closeConnection(){
            try{
                _writer.Close(); //Closes the output path to the client
                _reader.Close(); //Closes the input path to the server, from the client.
                _connection.Close(); //Closes the connection between you can the client
            }catch(IOException e){
                Console.WriteLine(e);
            }
        }
    }
}