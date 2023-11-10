package Socket.Prova_Esame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*; 

public class client {
    public  static void  main(String[] args) throws IOException{
        InetAddress addr = InetAddress.getByName(null);
        Socket socket = null; 
        BufferedReader in = null, stdIN = null; 
        PrintWriter out = null; 

        try{
            socket = new Socket(addr, 3333); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            out = new PrintWriter(socket.getOutputStream(), true); 
            stdIN = new BufferedReader(new InputStreamReader(System.in)); 

            String userInput; 
            while(true){
                userInput = stdIN.readLine(); 
                out.println(userInput);

                System.out.println("Server -> " + in.readLine());
            }
        }catch (UnknownError e){
            System.out.println("Error");
            System.exit(1);
        }

        in.close();
        out.close();
        stdIN.close();
        socket.close();
    }
}
