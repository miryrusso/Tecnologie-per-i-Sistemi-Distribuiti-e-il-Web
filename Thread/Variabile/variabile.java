package Variabile;

public class variabile {
    int n = 0; 
    public int getVar(){
        return(n); 
    }

    public synchronized void increase(int x){
        n+=x;
    }


}
