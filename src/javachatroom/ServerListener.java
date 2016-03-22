package javachatroom;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Timothy Flynn
 */
public class ServerListener extends Thread {
    
    //Create variables for input/output variables related to socket
    private final Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private ArrayList<Socket> clients;
    private int num;

    public ServerListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //Assign number to each client 
            num = clients.size() - 1;
            
            //Read input from the client
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //Initialize output stream and welcome user to chat room
            output = new PrintStream(socket.getOutputStream());
            output.println("\b\b\b\b\b\b Welcome to the chat room, you are user # " + (num+1)+" \n--------------------------------------------");
            
            //Listen for user input
            while (true) {
                //Listen using BufferedReader and format string nicely, then print to server console
                String inputString = input.readLine();
                inputString = "User " + (num + 1) + ": " + inputString + "\t";
                System.out.println(inputString);

                //Iterate through all clients (minus sender) and broadcast message
                for (int i = 0; i < clients.size(); i++) {
                    if (num != i) {
                        PrintStream toClient = new PrintStream(clients.get(i).getOutputStream(), true);
                        toClient.println(inputString);
                        toClient.flush();
                    }
                }
            }
        } 
        
        catch (IOException ex) {
            Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Use this to provide an updated list of client sockets
    public void setClients(ArrayList<Socket> clients) {
        this.clients = clients;
    }

}
