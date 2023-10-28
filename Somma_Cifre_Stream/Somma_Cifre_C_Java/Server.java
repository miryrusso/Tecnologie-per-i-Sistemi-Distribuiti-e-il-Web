import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 3333; //costante
    private static final int MAX_SIZE_BUFFER = 2048; //costante 
    private static int requestCounter = 0; //id da incrementare

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server ready..");

            while (true) {
                /**Quando una connessione in ingresso viene ricevuta, viene creata un oggetto Socket denominato connectionSocket 
                 * per gestire questa connessione. 
                 * Il metodo accept() blocca l'esecuzione fino a quando non viene stabilita una connessione. */
                Socket connectionSocket = serverSocket.accept();
                /**Viene creato un BufferedReader per leggere i dati inviati dal client attraverso l'input stream del socket. */
                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                /**Viene creato un BufferedWriter per scrivere dati di risposta attraverso l'output stream del socket, che verranno inviati al client.*/
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

                String buffer;
                while ((buffer = reader.readLine()) != null) {
                    System.out.print(buffer+'\n');
                    // Calcoliamo la somma delle cifre nella stringa ricevuta
                    int sum = sommacifre(buffer);
                    //facciamo Eco
                    //writer.write(buffer + "\n"); 
                    //writer.flush();
                    // Inviamo la risposta al client
                    String response = (requestCounter++) + ", " + sum + "\n";
                    writer.write(response);
                    /**
                     * Questo comando forza il BufferedWriter a scrivere i dati effettivamente sullo stream del socket.
                     */
                    writer.flush();
                }

                connectionSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funzione per calcolare la somma delle cifre in una stringa
    private static int sommacifre(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                sum += Character.getNumericValue(s.charAt(i));
            }
        }
        return sum;
    }
}
