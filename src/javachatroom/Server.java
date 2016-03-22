package javachatroom;

import java.net.*;
import java.util.*;

/**
 *
 * @author Timothy Flynn
 */
public class Server {

    //Create arraylist to store client sockets
    private static final ArrayList<Socket> clients = new ArrayList<Socket>();

    public static void main(String[] args) throws Exception {

        //Create server socket to listen for clients
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Socket open on port 9999...");

        //Listen for clients
        while (true) {
            //Accept any client requests and add them to the list
            Socket socket = serverSocket.accept();
            clients.add(socket);
            
            //Create new ServerListener instance to keep track of client
            ServerListener sl = new ServerListener(socket);
            sl.setClients(clients);
            sl.start();
            System.out.println("New connection. Client # " + clients.size());
        }
    }
}
