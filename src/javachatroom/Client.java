package javachatroom;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Timothy Flynn
 */
public class Client {

    public static void main(String[] args) throws Exception {

        //Connect to server
        Socket socket = new Socket("localhost", 9999);
        
        //Create new ClientListern instance to listen for messages from server
        ClientListener cl = new ClientListener(socket);
        cl.start();
        
        //Initialize PrintStream object to communicate with server
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        
        //Listen for keyboard inputs from user
        while (true) {
            //Prompt user and listen for input 
            System.out.print("You: ");
            Scanner scan = new Scanner(System.in);
            String message = scan.nextLine();

            //Send user message to server
            printStream.println(message);
            printStream.flush();
        }

    }

}
