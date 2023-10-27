/**
 * Thread (C o Java) - Tempo a disposizione: 30 minuti.
Scrivere un programma GiocoOca con 2 thread t0 e t1, che condividono la variabile intera turno con valore iniziale 0. 
Ciascun thread ha una propria variabile privata posizione, con valore iniziale 0.
II comportamento del thread tk (k=0, 1) è il seguente:
﻿﻿se turno=-1, termina
﻿﻿se no, verifica che sia il proprio turno, cioè che turno=k, altrimenti va in attesa con wait()
﻿﻿lancia un dado a dieci facce, sia d il risultato
﻿﻿incrementa la propria posizione di d
﻿﻿se posizione ha superato il valore 100, scrive: "100 superato", pone turno=-1, sveglia (con signall notify) l'altro thread e termina
﻿﻿altrimenti pone turno-1-k, sveglia (con signall notify) l'altro thread e ricomincia, dopo un ritardo di 500 m (variate pure il ritardo, 
se volete, per comodità di visualizzazione).
NB: per il thread k, l'altro thread è 1-k.
 */
public class app {
    public static void main(String [] args) throws Exception{
        Game g = new Game();
        Thread a = new Player(0,g); 
        Thread b = new Player(1,g); 
        a.start();
        b.start(); 
        a.join();
        b.join();
        System.out.println("Fine Partita...\n"); 

    }

}