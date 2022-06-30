using System;
using System.Collections.Generic;
using System.Diagnostics.Tracing;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace Server
{
    class Server
    {
        private TcpClient connection;
        private static TcpListener server;
        public static List<ServerHandler> connections = new List<ServerHandler>();
        public static HashSet<int> onlinePlayers =  new HashSet<int>();
        private int counter;
        public static int ballRecord;
        static void Main(string[] args)
        {
            new Server();
        }

        public Server()
        {
            try
            {           
                server = new TcpListener(IPAddress.Loopback, 7088);
                server.Start();
                Console.WriteLine("Game Server Started .............");
                Console.WriteLine("Waiting For Player  .............");
                counter = 0;
                while (true)
                {
                    counter++;
                     connection = server.AcceptTcpClient();
                    Console.WriteLine("New player ID: " + counter + " has joined the game.");
                    ServerHandler playerthread = new ServerHandler(connection,server,counter);
                    new Thread(playerthread.run).Start();
                    connections.Add(playerthread);
                }
            }
            catch (IOException e)
            {
                Console.WriteLine(e);
            }
        }
    }
}