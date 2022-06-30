using System;
using System.IO;
using System.Net.Sockets;

namespace Client
{
    public class ClientHandler
    {
        private TcpClient _connection;
        private Stream stream;
        private static StreamWriter _writer;
        private StreamReader _reader;
        private bool running = true;

        public ClientHandler(TcpClient connection, Client client)
        {    
            
            this._connection = connection;
        }

        public void run()
        {
            try
            {
                stream = _connection.GetStream();
                _reader = new StreamReader(stream);
                _writer = new StreamWriter(stream);
                while (running)
                {
                    try
                    {
                        Console.WriteLine("listening...............");
                        sendRequestToServer("WHO");
                        string serverMessage = _reader.ReadLine();
                        Console.WriteLine(serverMessage);
                    }
                    catch (IOException e)
                    {
                        Console.WriteLine(e);
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                Console.WriteLine("Disconnected From the Server, the program has been terminated.");
            }

        }



        public static void sendRequestToServer(String playerRequest)
        {
            try
            {
                _writer.Write(playerRequest);
                _writer.Flush();
            }
            catch (IOException e)
            {
                Console.WriteLine(e);
            }
        }


        public void closeGame()
        {
            try
            {
                _connection.Close();
                _reader.Close();
                _writer.Close();
            }
            catch (IOException e)
            {
                Console.WriteLine(e);
            }
        }
        
    }
}
