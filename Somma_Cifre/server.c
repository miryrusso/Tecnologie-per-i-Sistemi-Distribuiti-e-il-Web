/**
 * Esercizio 1: Socket (C o Java) -- Tempo a disposizione: 60 minuti.

Realizzare un server (in C o in Java) che si metta in ascolto sul port 3333, sul quale accetta richieste di connessione da un client. 
Sulla connessione stabilita, il server riceve una stringa, terminata dal carattere '\n' (si supponga che basti una sola operazione di lettura in ricezione da parte del server, per ricevere ciascuna stringa inviata dal client). 
Il server quindi scrive la stringa ricevuta sullo standard output e, chiusa la connessione, si rimette in attesa di eventuali richieste di connessione. 
Testare il server server_A usando telnet. 
Conservando la versione (A) del server, realizzarne un’ulteriore versione (B), tale che ciascuna stringa ricevuta dal server, oltre ad essere scritta dal server sulla propria standard output, sia inviata nuovamente al client come risposta (senza alcuna modifica). 
Testare il server server_B usando telnet.
Conservando la versione (B) del server, realizzarne un’ulteriore versione tale che il server implementi un metodo/funzione 
				         sommacifre(s) 
che accetta per argomento una stringa s e restituisce un intero;  per il momento, ai fini di questo quesito (C), si supponga che, per qualunque stringa argomento s, venga restituito l’intero 0. In questa versione, il server, ricevuta una stringa s la scrive sulla standard output, calcola x = sommacifre(s) e invia al client un messaggio in forma di stringa  del tipo
					n,x
dove n è un id progressivo della richiesta fatta dal client, mentre x è l’output del metodo sommacifre(s). L’id, che fa parte dello stato del server, parte da 0 e viene incrementato ad ogni nuova richiesta da parte di un client fino a quando il server non viene riavviato.
Testare il server server_C usando telnet. 
Modificare la funzione sommacifre(s) in modo che, riconosciute le cifre numeriche al suo interno, restituisca la somma delle singole cifre o 0 se non ve ne fosse alcuna. 
Esempio: sommacifre("c0a89s2a3hk") restituirà 22 (dato che 0+8+9+2+3 = 22).
(Opzionale) Realizzare un semplice client per testare i server creati ai punti precedenti.
N.B.: Consegnare tutti e solo i file sorgente prodotti, impacchettati in un file compresso denominato es1_nome_cognome_matricola.zip
*/