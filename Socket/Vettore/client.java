package Socket.Vettore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*; 

public class client {
    public static void main(String[] args) throws IOException{
        Socket socket = null; 
        InetAddress addr = InetAddress.getByName(null); 
        BufferedReader in = null, stdIn = null; 
        PrintWriter out = null; 

        try{
            socket = new Socket(addr, 7777); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            stdIn = new BufferedReader(new InputStreamReader(System.in)); 
            out = new PrintWriter(socket.getOutputStream(), true); 

            String userInput; 
            while(true){
                userInput = stdIn.readLine(); 
                out.println(userInput);

                System.out.println("Server response " + in.readLine());
                if(in.readLine() == null){
                    break; 

                }
            }
        }catch(UnknownError e){
            System.out.println("Error");
            System.exit(1); 
        }

        System.out.println("Client closed..");
        in.close();
        out.close();
        stdIn.close();
        socket.close();

    }
}
