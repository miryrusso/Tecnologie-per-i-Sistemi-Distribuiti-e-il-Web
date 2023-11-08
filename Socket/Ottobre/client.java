package Socket.Ottobre;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class client {
    public static void main(String [] args) throws IOException{
        InetAddress addr = InetAddress.getByName(null); 
        Socket s = null; 
        BufferedReader in = null, stdIO = null; 
        PrintWriter out = null; 

        try{
            s = new Socket(addr, 7777); 
            in = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            out = new PrintWriter(s.getOutputStream()); 
            stdIO = new BufferedReader(new InputStreamReader(System.in)); 

            String userInput; 
            while(true){
                userInput = stdIO.readLine(); 
                out.println(userInput);

                System.out.print("[RISPOSTA SERVER " + in.readLine());
            }
        }catch(UnknownError e) {}

        in.close();
        out.close();
        stdIO.close();
        s.close();
    }
}
