/**
 * Compilazione -> javac client.java
 * Esecuzione -> java client
 */
import java.io.*;
import java.net.*;

public class client{
    public static void main(String[] args) throws IOException{
        InetAddress addr = InetAddress.getByName(null); //null per localhost 
        Socket socket = null; 
        BufferedReader in = null, stdIn= null; 
        PrintWriter out = null;

        try {
            socket = new Socket(addr, 7777); 
            System.out.println("Started...\n"); 

            //IN legge da server 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //out , true serve per svuotare il buffer
            out = new PrintWriter(socket.getOutputStream(), true); 

            //keyboard 
            stdIn = new BufferedReader(new InputStreamReader(System.in)); 

            String userInput;

            while(true){
                userInput = stdIn.readLine(); 
                out.println(userInput); //print sul server, corrisponde alla write


                System.out.println("Server response: " + in.readLine());



            } 

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + addr);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + addr);
            System.exit(1);
        }

        System.out.println("EchoClient: closing...");
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}