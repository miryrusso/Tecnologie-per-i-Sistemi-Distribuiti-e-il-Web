/**
 * Tecniche di Programmazione Concorrente e Distribuita  24/05/2016

    1. Socket (C o Java)

    Scrivere un server che si metta in ascolto sul port 3333 in grado di rispondere ad un messaggio composto 
    da una sola stringa str composta da cifre numeriche (da 0 a 9) terminata dal carattere \n.

    Il server:
        -Converte la stringa ricevuta str in un numero intero n.
        -Il numero viene usato come input per il metodo “int cubo(int n)” che restituisce il cubo dell’intero n.
        -Il server invia al client il valore restituito dal metodo cubo(). 

    Testare il server con un semplice client o con telnet.

    Tempo a disposizione: 35 minuti.
*/


#include <stdlib.h>
#include <stdio.h>
#include <arpa/inet.h>
#include <string.h>

#define PORT 3333
#define IP "127.0.0.1"
#define MAX_SIZE_BUFFER 1024

int cubo(int n){
    return n*n*n; 
}

int main(int argc, char**argv){
    int s; 
    int readcode = 0 ; 
    char buffer[MAX_SIZE_BUFFER]; 
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
   
    int check = 1;

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        return -1;
    }

    if((setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);
    
    if((bind(s, (struct sockaddr *)& server, len)) < 0){
        perror("bind()\n");
        return -1;
    }

    if(listen(s, 1) != 0){
        perror("listen()\n");
        return -1;
    }

    printf("Server is ready...\n");


    int connSocket;
    if((connSocket = accept(s, (struct sockaddr *)&client, &len)) < 0){
        perror("accept()\n");
        return -1;
    }

    while(1){
        int conn; 
        printf("Prima della read..\n");
        if((conn = read(connSocket, &buffer, MAX_SIZE_BUFFER)) <0){
            perror("Read problem...\n"); 
            exit(-1);
        }

        buffer[conn] = '\0'; 
        //fai qualcosa
        //int converti = (int)buffer - '0'; 
       
       int n = atoi(buffer); 

       printf("Il numero convertito dal buffer è il seguente : %d \n", n); 
       
       sprintf(buffer, "Il numero è il seguente: %d \n", n);

        write(connSocket, &buffer, strlen(buffer)); 

        sprintf(buffer, "Il numero è il seguente: %d \n", cubo(n));

        write(connSocket, &buffer, strlen(buffer)); 
    }

    close(s); 

}
