package javachatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timothy Flynn
 */
public class ClientListener extends Thread {

    private final Socket socket;

    ClientListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStreamReader inputStream = null;
        
        try {
            //Initialize inputStream from socket and created BufferedReader
            inputStream = new InputStreamReader(socket.getInputStream());
            BufferedReader buffReader = new BufferedReader(inputStream);
            
            //Listen for messages coming from the server
            while (true) {
                String s = buffReader.readLine();
                //Formatting to make chat appear nicely
                System.out.print("\b\b\b\b\b" + s + "\nYou: ");
            }
        } 
        
        catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        finally {
            try {
                inputStream.close();
            } 
            
            catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}