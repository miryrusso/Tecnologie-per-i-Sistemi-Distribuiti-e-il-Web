package Numero_casuale;

public class Value {
    int n;

    public synchronized void setVar(int n){
        this.n = n;
    }

    public synchronized int getVar(){
        return n;
    }
}

