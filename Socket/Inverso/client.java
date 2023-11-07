package Socket.Inverso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; 
import java.net.*; 
public class client {
    public static void main(String [] argc) throws IOException{
        Socket socket = null; 
        InetAddress addr = InetAddress.getByName(null); 
        BufferedReader in = null, stdIn = null; 
        PrintWriter out = null; 

        try{
            socket = new Socket(addr, 5533);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            out = new PrintWriter(socket.getOutputStream(), true); 
            stdIn = new BufferedReader(new InputStreamReader(System.in)); 

            String userInput; 
            while(true){
                userInput = stdIn.readLine(); 
                out.println(userInput);

                System.out.println("[RISPOSTA SERVER] : "+in.readLine());

            }

        }catch(UnknownError e){
            System.out.println("Errore");
            System.exit(1); 
        }

        in.close();
        out.close();
        stdIn.close();
        socket.close();
    }
}
