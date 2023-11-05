package Variabile;
import java.util.Random;

public class ThreadTO extends Thread{
    variabile n; 
    ThreadTO(variabile n){
        this.n = n; 
    }
    /**
     * Il thread tO, ciclicamente:
-attende 200 ms (N.B.: la chiamata usleep(n) attende per n microsecondi)
-genera un int casuale dispari e lo somma alla variabile condivisa n
-se ha eseguito almeno 10 cicli e n è dispari termina
-altrimenti ricomincia dal passo (1), a meno che abbia già compiuto 1000 iterazioni, nel qual caso termina.

     */
    int rnd;
    int count = 0;
    
    

    @Override
    public void run(){
        for(;;){
            try {sleep(200);}
            catch(InterruptedException e){}

            Random r = new Random();
            rnd = (r.nextInt(50) * 2) + 1;
            n.increase(rnd);
            System.out.println("Value: " + n.getVar() + " from " + getName() );

            if(count >= 10 && (n.getVar()%2) == 1){
                break;
            }else if(count >= 11){
                break;
            }

            count++;
        }
    }
}
