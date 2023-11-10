package Tiro_alla_fune.Seconda_versione;

public class posizione {
    int posizione = 0; 

    public synchronized int get(){
        return(posizione);
    }

    public synchronized void set(int posizione){
        this.posizione = posizione; 
    }

    public synchronized void gameWait() throws InterruptedException{
        wait(); 
    }

    public synchronized void gameNotify() throws InterruptedException{
        notifyAll();
    }
}
