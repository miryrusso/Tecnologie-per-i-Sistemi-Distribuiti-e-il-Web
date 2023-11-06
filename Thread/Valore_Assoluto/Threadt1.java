package Valore_Assoluto;

import java.util.*;
public class Threadt1 extends Thread {
    variabile x; 
    private int m = 0; 

    Threadt1(variabile x){
        this.x = x; 
    }

    /**
     * Il thread **T1** ha una **variabile privata m** ed esegue un ciclo infinito, comportandosi in ciascun ciclo come segue:
- Attende 100ms
- Genera un valore casuale intero compreso tra 0 e 10 (estremi inclusi) e lo memorizza in **m**
- Se **x** è uguale a `-1` termina l'esecuzione
- Altrimenti, confronta m con la variabile condivisa **x**:
- Se **m** e **x** coincidono stampa un messaggio "RISPOSTA CORRETTA", setta **x** a `-1` e termina l'esecuzione
- Se la differenza in valore assoluto tra **m** ed **x** è **maggiore di 5** stampa il messaggio "risposta MOLTO sbagliata" 
e si mette in attesa
- Altrimenti, stampa il messaggio "risposta sbagliata"

     */

    @Override
    public void run(){
        //sviluppo soluzione
        for(;;){
            try{
                sleep(100); 
            }catch(InterruptedException e) {};
            
            Random rnd = new Random(); 
            m = (rnd.nextInt(11)); 
            System.out.println("[THREAD "+getName() + ", m = " + m); 

            if(x.getVar() == -1){
                System.out.println("[ESECUZIONE TERMINATA] "); 
                break; 
            }

            if(x.getVar() == m){
                System.out.println("[RISPOSTA CORRETTA]"); 
                x.setVar(-1);
                break; 
            }

            int differenza = 0;

            differenza = valore_assoluto(m,x.getVar()); 

            if(differenza > 5){
                System.out.println("[RISPOSTA MOLTO SBAGLIATA] \n");
                try{
                    x.gameWait();
                }catch(InterruptedException e) {}; 
                
            }else{
                System.out.println("[RISPOSTA SBAGLIATA] \n");
            }


        }
    }

    int valore_assoluto(int m, int x){
        return Math.abs(m-x); 
    }
}
