package Socket.Cifre_numeriche;
import java.io.*; 
import java.net.*; 

public class client {
    public static void main(String []args)throws IOException{
        Socket socket = null; 
        InetAddress addr = InetAddress.getByName(null);
        BufferedReader in = null, stdIn = null; 
        PrintWriter out = null; 

        try{
            socket = new Socket(addr, 7777); 
            System.out.println("Started"); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            out = new PrintWriter(socket.getOutputStream(), true); 

            stdIn = new BufferedReader(new InputStreamReader(System.in)); 
            String userInput; 

            while(true){
                userInput = stdIn.readLine(); 
                out.println(userInput);
                System.out.println("Server response " + in.readLine());
            }
        }catch(UnknownHostException e){
            System.out.println("Error.."); 
            System.exit(1);
        }

        System.out.println("Closing Connections..."); 
        out.close();
        in.close();
        stdIn.close();
        socket.close();

        

    }
    

}
