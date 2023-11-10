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


#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>

#define IP "127.0.0.1"
#define PORT 7775
#define MAX_BUFFER 1024
int cubo(int numero ){
    return(numero * numero * numero); 
}



int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER]; 
    int connection; 


    if((s = socket(AF_INET, SOCK_STREAM, 0))<0){
        perror("Apertura"); 
        exit(-1); 
    }


    memset(&server, 0 , len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT); 

    if((bind(s, (struct sickaddr *)&server, len)) < 0){
        perror("Bind error"); 
        exit(-1); 
    }

    printf("Server ready..\n"); 

    if((listen(s, 1)) != 0){
        perror("Listen Problem"); 
        exit(-1); 
    }

    

    if((connection = accept(s, (struct sockaddr *)&client, &len))<0){
        perror("Accept server"); 
        exit(-1); 
    }

    while(1){
        int n; 
        if((n = read(connection, &buffer, MAX_BUFFER)) < 0){
            perror("Read Problem \n"); 
            exit(-1); 
        }

        buffer[n] = '\0'; 
        int numero = atoi(buffer); 
        printf("Il numero generato è : %d \n", numero); 

        sprintf(buffer, "Il numero è il seguente %d", cubo(numero)); 

        write(connection, &buffer, strlen(buffer)); 


    }

}