package Numero_casuale;
import java.util.Random;

public class Generatore extends Thread {
    Value n;

    Generatore(Value n){
        this.n = n;
        n.setVar(0);
    }
    
    public void run(){
        n.setVar(0);
        for(;;){
            Random rnd = new Random(); 
            n.setVar((rnd.nextInt(100) + 1));
            System.out.println("[NUMERO GENERATO] "+ n.getVar()); 

            try{
                sleep(500);
            }catch(InterruptedException e){}; 
        }
        
    }
}
