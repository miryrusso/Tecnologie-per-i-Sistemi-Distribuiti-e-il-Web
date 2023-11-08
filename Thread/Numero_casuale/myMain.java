/**
 * Ecco un altro esercizio che coinvolge due thread senza l'utilizzo delle code condivise:

Titolo: Thread (Java) [Esercizio]

Tempo a disposizione: 45 minuti

Obiettivo: Scrivere un programma Java con due thread: uno generatore e uno consumatore. Il generatore genera numeri casuali e il consumatore li elabora.

Descrizione:

Creare due thread in Java: un generatore (G) e un consumatore (C).

Thread Generatore (G):

Inizializza una variabile condivisa numeroGenerato a 0.
Esegue un ciclo infinito.
Genera un numero intero casuale compreso tra 1 e 100 (estremi inclusi) e lo assegna a numeroGenerato.
Stampa un messaggio che indica il numero generato.
Attende 500 ms prima di generare un nuovo numero.
Thread Consumatore (C):

Esegue un ciclo infinito.
Controlla il valore di numeroGenerato.
Se numeroGenerato è pari, calcola e stampa il quadrato di numeroGenerato.
Se numeroGenerato è dispari, calcola e stampa il doppio di numeroGenerato.
Attende 300 ms prima di controllare il valore di numeroGenerato nuovamente.
Nota: Assicurati di utilizzare i meccanismi di sincronizzazione adeguati per evitare problemi di concorrenza tra i due thread. 
In questo esercizio, numeroGenerato 
è la variabile condivisa tra i due thread, e il consumatore deve aspettare che il generatore produca un nuovo numero prima di elaborarlo.
 */
package Numero_casuale;


/*
Titolo: Thread (Java) [Esercizio]

Tempo a disposizione: 45 minuti

Obiettivo: Scrivere un programma Java con due thread: uno generatore e uno consumatore. 
Il generatore genera numeri casuali e il consumatore li elabora.

Descrizione:

Creare due thread in Java: un generatore (G) e un consumatore (C).

Thread Generatore (G):

Inizializza una variabile condivisa numeroGenerato a 0.
Esegue un ciclo infinito.
Genera un numero intero casuale compreso tra 1 e 100 (estremi inclusi) e lo assegna a numeroGenerato.
Stampa un messaggio che indica il numero generato.
Attende 500 ms prima di generare un nuovo numero.

Thread Consumatore (C):

Esegue un ciclo infinito.
Controlla il valore di numeroGenerato.
Se numeroGenerato è pari, calcola e stampa il quadrato di numeroGenerato.
Se numeroGenerato è dispari, calcola e stampa il doppio di numeroGenerato.
Attende 300 ms prima di controllare il valore di numeroGenerato nuovamente.

Nota: Assicurati di utilizzare i meccanismi di sincronizzazione adeguati per evitare problemi di concorrenza 
tra i due thread. In questo esercizio, numeroGenerato è la variabile condivisa tra i due thread, e il consumatore
 deve aspettare che il generatore produca un nuovo numero prima di elaborarlo. */
 public class myMain {
    public static void main(String [] args)throws InterruptedException{
        Value n = new Value(); 
        Generatore tg = new Generatore(n); 
        Consumatore tc = new Consumatore(n); 

        tg.start();
        tc.start();
        
        
    }
}

