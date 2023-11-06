package Valore_Assoluto;

public class app {
    public static void main(String [] args) throws InterruptedException{
        variabile x = new variabile(); 
        Threadt1 t1 = new Threadt1(x);
        Threadt2 t2 = new Threadt2(x);

        t1.start(); 
        t2.start();

        x.getVar(); 
        t1.join();
        t2.join();
        System.out.println("[FINE PROGRAMMA] \n");
    }
}
