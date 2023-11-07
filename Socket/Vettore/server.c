/**
 * Implementare un server, in C o Java, che risponde sul port 7777.
Il server mantiene un vettore V con le ultime dieci richieste (stringhe di 10 caratteri) ricevute.
Le richieste a cui il server deve rispondere sono:

1. la stringa "LIST", a cui il server risponde inviando al client tutte le risposte finora memorizzate in V, separate da "In';
    2. qualsiasi altra stringa viene trattata come messaggio da inserire nel vettore V:
    2.1 se la stringa è già presente in V, il server risponde con "presente"; 
    2.2 se la stringa non è ancora presente in V, il server la aggiunge a V, eventualmente sovrascrivendone una a caso tra quelle già esistenti, e risponde "inserita" al client.
Dopo avere risposto, il server chiude la connessione con il client e torna ir
attesa di richieste.
Facoltativo per la prova in itinere, obbligatorio per l'esame completo:
*/
#include<stdlib.h>
#include<stdio.h>
#include<arpa/inet.h>
#include<string.h>

#define MAXBUFF 10
char vect[10][MAXBUFF];
int pos = 0;

int main(int argc, char** argv){
    char buffer[MAXBUFF];
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
    int check = 1;
    

    int s;

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        return -1;
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(7777);

    if((setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

    if((bind(s, (struct sockaddr *)&server, len)) < 0){
        perror("bind()\n");
        return -1;
    }

    if((listen(s, 1)) != 0){
        perror("listen()\n");
        return -1;
    }

    printf("Sever ready\n");

    while(1){
        int connSocket;
        if((connSocket = accept(s, (struct sockaddr *)&client, &len)) < 0){
            perror("accept()\n");
            return -1;
        }

        int n;
        if((n = read(connSocket, &buffer, len)) < 0){
            perror("read()\n");
            return -1;
        }

        printf("%s", buffer);

        buffer[n] = 0;

        checkString(buffer, connSocket);

        close(connSocket);
    }

}

void checkString(char str[MAXBUFF], int conn){
    if(strstr(str, "LIST") != NULL){
        int totlen = 0; 
        //calcolo la lunghezza finale totale dii buff
        for(int i = 0; i < pos; i++){
            totlen += sizeof(vect[i]) + 4;
        }

        char *buff = malloc(totlen + 1);
        buff[totlen] = 0;

        for (int i = 0; i < pos; i++) {
            strcat(buff, vect[i]);
            strcat(buff, " In ");
        }

        printf("%s\n", buff);

        write(conn, buff, totlen);

        free(buff); //libero la memoria di buff, che rialloco ad ogni ciclo;

    }else{
        for(int i = 0; i < 10; i++){
            if(strcmp(str, vect[i]) == 0){
                write(conn, "presente\n", 10);
                return;
            }
        }
        if(pos < 10)
            strcpy(vect[pos++], str);
        else{
            int n = (int) rand() % 10;
            strcpy(vect[n], str);
        }
        write(conn, "inserita\n", 10);      
    }
}