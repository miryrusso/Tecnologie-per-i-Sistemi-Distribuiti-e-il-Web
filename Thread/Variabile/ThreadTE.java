package Variabile; 
import java.util.Random;

public class ThreadTE extends Thread{
    variabile n;
    int rnd;
    int count=0;
    
    ThreadTE(variabile n){
        System.out.println("Constructor of "+getName());
        this.n = n;
    }

    @Override
    public void run(){
        for(;;){
            try {sleep(200);}
            catch(InterruptedException e){}

            Random r = new Random();
            rnd = (r.nextInt(50) * 2);
            n.increase(rnd);
            System.out.println("Value: " + n.getVar() + " from " + getName() );

            if(count >= 10 && (n.getVar()%2) == 0){
                break;
            }else if(count >= 11){
                break;
            }

            count++;
        }
    }
}
