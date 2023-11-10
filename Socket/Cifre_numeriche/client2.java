package Socket.Cifre_numeriche;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class client2 {
    public static void main(String [] args) throws IOException{

    
        Socket socket = null; 
        InetAddress addr = InetAddress.getByName(null);
        BufferedReader in = null, stdIN = null; 
        PrintWriter out = null; 

        try{
            socket = new Socket(addr, 7775); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            stdIN = new BufferedReader(new InputStreamReader(System.in)); 
            out = new PrintWriter(socket.getOutputStream(), true); 

            String userString; 
            while(true){
                userString = stdIN.readLine(); 
                out.println(userString);

                System.out.println("[SERVER ] -> "+ in.readLine());
            }
        }catch(UnknownError e){
            System.out.println("Si è verificato un errore ");
            System.exit(1);
        }

        in.close(); 
    }   
    
}
