using System;
using System.Net.Sockets;
using System.Threading;

namespace Client
{
    public class Client
    {
        private ClientHandler ch;
        private TcpClient connection;
        private static TcpListener server;
        public static void Main(String[] args)
        {
            new Client();
        }

        public Client()
        {
            try
            {
                connection = new TcpClient("localhost", 7088);
                Console.WriteLine("Connected to the main server ......");
                ch = new ClientHandler(connection,this);
                new Thread(ch.run).Start();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
        }
    }
}