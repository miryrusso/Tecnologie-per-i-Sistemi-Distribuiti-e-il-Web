package Numero_casuale;
import java.util.*;

public class Consumatore extends Thread {
    Value n;
    Consumatore(Value n){
        this.n = n; 
    }

    @Override
    public void run(){
        /**
         * Thread Consumatore (C):

            Esegue un ciclo infinito.
            Controlla il valore di numeroGenerato.
            Se numeroGenerato è pari, calcola e stampa il quadrato di numeroGenerato.
            Se numeroGenerato è dispari, calcola e stampa il doppio di numeroGenerato.
            Attende 300 ms prima di controllare il valore di numeroGenerato nuovamente.
            Nota: Assicurati di utilizzare i meccanismi di sincronizzazione adeguati per evitare problemi di concorrenza tra i due thread. 
            In questo esercizio, numeroGenerato  è la variabile condivisa tra i due thread, e il consumatore deve aspettare 
            che il generatore produca un nuovo numero prima di elaborarlo.
        */

        for(;;){
            n.getVar(); 
            int stampa_numero = 0;

            if(n.getVar() % 2 == 0){
                 
                n.setVar((n.getVar() * n.getVar()));
                stampa_numero = n.getVar();
                System.out.println("[NUMERO PARI] - QUADRATO :  " + stampa_numero); 

            }else if(n.getVar() % 2 != 0){
                n.setVar((n.getVar() * 2));
                stampa_numero = n.getVar();
                System.out.println("[NUMERO DISPARI] - DOPPIO : " + stampa_numero); 
            }

            try{
                sleep(300); 
            }catch(InterruptedException e){};
        }
    }
    
}
