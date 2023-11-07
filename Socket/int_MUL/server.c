/**
 * Socket (C o Java)
 * Realizzare un server (in C o in Java) chiamato Server A che si metta in ascolto sul port
7777 per ricevere una stringa str composta da una sequenza di lunghezza
arbitraria di caratteri numerici da (0 a 9) e terminata dal carattere In. Il server dovrà quindi stampare 
il messaggio ricevuto sullo standard output. Testare il server usando telnet.

Estendere le funzionalità dal server definito al punto precedente realizzando un secondo server chiamato Server B che oltre a stampare 
il messaggio ricevuto sullo standard output, lo invia come risposta al client (senza modificarlo). Testare il server usando telnet.

Estendere le funzionalità dal server definito al punto precedente realizzando un terzo server chiamato Server C che oltre a stampare 
il messaggio ricevuto sullo standard output, lo passa ad un metodo "int MUL (String str)" che per ora restituisce sempre 0 per qualunque 
parametro di input str. II risultato del metodo deve quindi essere inviato come messaggio di risposta al client. Testare il server usando 
telnet.

Estendere le funzionalità dal server definito al punto precedente realizzando un quarto server chiamato Server D modificando il 
comportamento del metodo "int
MUL (String str)" che dovrà restituire il prodotto delle singole cifre numeriche presenti nella stringa in input, ad esempio 
per la stringa "1234" restituirà l'intero 24. Il risultato del metodo deve quindi essere inviato come messaggio di risposta al client.
Testare il server usando telnet.
*/

#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>

#define PORT 7777
#define IP "127.0.0.1"
#define MAX_BUFFER 1024

int MUL (char * str){
    int result = 1;
    int length = strlen(str);

    for (int i = 0; i < length; i++) {
        if (str[i] >= '0' && str[i] <= '9') {
            result *= (str[i] - '0');
        }
    }

    return result;
}

int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER]; 

    //APERTURA DELLA SOCKET 
    if((s = socket(AF_INET, SOCK_STREAM, 0))<0){
        perror("Inizializzazione socket...\n"); 
        exit(-1); 
    }

    printf("Server ready...\n"); 

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT); 

    if((bind(s, (struct sockaddr *)&server, len)) < 0){
        perror("Errore nella bind ... \n"); 
        exit(-1); 
    }


    //listen 
    if(listen(s, 1) != 0){
        perror("Listern problem...\n"); 
        exit(-1); 
    }


    int connectionSocket; 
    if((connectionSocket = accept(s, (struct sockaddr *)&client, &len)) < 0){
        perror("Accept problem...\n"); 
        exit(-1); 
    }


    while(1){
        int n; 
        if((n = read(connectionSocket, &buffer, MAX_BUFFER)) < 0){
            perror("Read problem... \n"); 
            exit(-1); 
        }

        //punto A
        printf("[MESSAGE TEXT] %s \n", buffer); 
        buffer[n] = '\0';

        //punto B 
        write(connectionSocket, &buffer, strlen(buffer)); 

        //punto C/D

        sprintf(buffer, "Il risultato della moltiplicazione : %d \n", MUL(buffer)); 
        write(connectionSocket, &buffer, strlen(buffer)); 
       
    }

    close(s); 
}