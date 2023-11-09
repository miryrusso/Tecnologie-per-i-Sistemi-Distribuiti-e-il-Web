package Socket.Messaggio_Ricevuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

public class client {
    public static void main(String[] args)throws IOException{
        InetAddress addr = InetAddress.getByName(null); 
        Socket socket = null; 
        BufferedReader in = null, stdIO = null; 
        PrintWriter out = null; 

        try{
            socket = new Socket(addr, 7777); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            stdIO = new BufferedReader(new InputStreamReader(System.in)); 
            out = new PrintWriter(socket.getOutputStream(), true); 

            String userInput;
            while(true){
                userInput = stdIO.readLine(); 
                out.println(userInput);

                System.out.println("[SERVER] " + in.readLine());
            } 

        }catch(UnknownError e){
            System.out.println("Errore\n");
            System.exit(1);
        }

    }
}
