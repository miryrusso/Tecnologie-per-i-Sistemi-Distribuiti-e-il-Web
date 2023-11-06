package Valore_Assoluto;
import java.util.*; 

public class variabile {
    //I thread hanno una **variabile condivisa x** inizializzata con un intero compreso tra 0 e 10 (*estremi inclusi*).


    Random rnd = new Random();
    
    int x = (rnd.nextInt(11));  
    
    public int getVar(){
        System.out.println(", x = " + x); 

        return (x); 
    }

    public synchronized void setVar(int x){
        this.x = x; 
    }

    public synchronized void gameWait() throws InterruptedException{
        wait(); 
    }

    public synchronized void gameNotify() throws InterruptedException{
        notifyAll();
    }
}
