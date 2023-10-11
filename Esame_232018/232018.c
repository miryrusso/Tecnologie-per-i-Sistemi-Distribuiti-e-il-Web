/**
 * Realizzare un server (in C o in Java) che si metta in ascolto sul port 3333, sul quale accetta richieste di connessione da un client.
Sulla connessione stabilita, il server riceve una stringa, terminata dal carattere In° (si supponga che basti una sola operazione 
di lettura in ricezione da parte del server, per ricevere ciascuna stringa inviata dal client).
Il server quindi scrive la stringa ricevuta sullo standard output e, chiusa la connessione, si rimette in attesa di eventuali 
richieste di connessione.

Testare il server server_A usando telnet.


B. Conservando la versione (A) del server, realizzare un'ulteriore versione (B), 
tale che ciascuna stringa ricevuta dal server, oltre ad essere scritta dal server sulla propria standard output, 
sia inviata nuovamente al client come risposta (senza alcuna modifica).

Testare il server server_B usando telnet.
C. Conservando la versione (B) del server, realizzarne un'ulteriore versione tale che il server implementi un metodo/funzione
sommacifre (s)
che accetta per argomento una stringa se restituisce un intero; per il momento, 
ai fini di questo quesito (C), si supponga che, per qualunque stringa argomento s, venga restituito l'intero 0. 
In questa versione, il server, ricevuta una stringa s la scrive sulla standard output, calcola × = sommacifre (s) 
e invia al client un messaggio in forma di stringa del tipo n, x
dove n è un id progressivo della richiesta fatta dal client, mentre x è l'output del metodo sommaci fre (s). 
L'id, che fa parte dello stato del server, parte da 0 e viene incrementato ad ogni nuova richiesta da parte di un client 
fino a quando il server non viene riavviato.
Testare il server server_C usando telnet.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <ctype.h>


#define PORT 3333
#define MAX_SIZE_BUFFER 2048

int sommacifre(char *s){
    int sum = 0; 

    for(int i = 0; i<strlen(s); i++){
        if(isdigit(s[i])){
            sum += s[i] - '0'; 
        }
    }

    return sum ; 
}

int main(int argc, char **argv){
    char buffer[MAX_SIZE_BUFFER];
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(client); 
    int s; 
    int check = 1; 
    int connectionSock;
   
    //Punto C
    int id = 0; 
    
    if((s = socket(AF_INET, SOCK_STREAM,0)) < 0){
        perror("Apertura socket\n");
        exit(-1); 
    }

    if((setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        exit(-1);
    }

    memset(&server, 0, sizeof(server)); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT);


    if((bind(s, (struct sockaddr *)&server, sizeof(server))) < 0){
        perror("bind\n");
        exit(-1);
    }

    printf("Server ready.. \n"); 

    if(listen(s,1) != 0){
        perror("Listen.. \n"); 
        exit(-1);
    }

    if((connectionSock = accept(s, (struct sockaddr *)&client, &len)) < 0){
            perror("Connection accept()");
            exit(-1);
        }

    while(1){
        //A) 
        //accettiamo nuove connessioni con accept 
         int retcode;
        

        //leggiamo da connectionSock e lo inseriamo il buffer.
        if ( (retcode = read(connectionSock, &buffer, MAX_SIZE_BUFFER)) < 0 ) {
            perror("read()\n");
            exit(-1);
        }

        buffer[retcode] = '\0'; 
        printf("%s", buffer); 

        //Punto B fare echo 
        write(connectionSock, &buffer, strlen(buffer));

        //PUNTO C
        int sum = sommacifre(buffer); 

        id++; 

        snprintf(buffer, sizeof(buffer), "%d , %d\n\0", id, sum ); 

        write(connectionSock, &buffer, strlen(buffer)); 

    }

    close(connectionSock); 

}
