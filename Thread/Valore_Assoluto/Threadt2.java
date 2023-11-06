package Valore_Assoluto;

/**
 * Il Thread **T2** invece:
- Attende 300ms
- Sveglia **T1**
- Se **x** è uguale a `-1`, termina l'esecuzione
- Altrimenti, ricomincia dal primo punto
 */

public class Threadt2 extends Thread{
    variabile x; 
    Threadt2(variabile x){
        this.x = x; 
    }

    @Override
    public void run(){
        //sviluppo soluzione
        for(;;){
            try{
                sleep(300);
                x.gameNotify();
            }catch(InterruptedException e){}; 

            if(x.getVar() == -1){
                System.out.println("[ESECUZIONE TERMINATA] \n"); 
                break;
            }
        }

    }
}
