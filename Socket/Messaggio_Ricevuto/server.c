/**
 * In altre parole, quando il server riceve un messaggio dal client, 
 * invierà sempre il messaggio "Messaggio ricevuto dal server" come risposta. Questa è una risposta statica.
*/

#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>

#define MAX_BUFFER 1024
#define IP "127.0.0.1"
#define PORT 7777

int main(int argc, char **argv){
    int s; 
    char buffer[MAX_BUFFER]; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char responce[MAX_BUFFER];
    int check = 1; 

    if((s = socket(AF_INET, SOCK_STREAM, 0))<0){
        perror("Errore nell'apertura della socket"); 
        exit(-1); 
    }

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT); 

    if((setsockopt(s,SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

    if(bind(s, (struct sockaddr *)&server, len)<0){
        perror("Errore nell'apertura della socket"); 
        exit(-1); 
    }

    printf("Server rwady \n"); 

    if(listen(s,1) != 0){
        perror("Errore nella listen della socket"); 
        exit(-1); 
    }

    int connection; 
    if((connection = accept(s, (struct sockaddr *)&client, &len))<0){
        perror("Errore accept della socket"); 
        exit(-1); 
    }

    while(1){
        int n; 
        if((n = read(connection, &buffer, MAX_BUFFER)) < 0){
            perror("READ"); 
            exit(-1); 
        }

        //fai cose
        char response[MAX_BUFFER]; 
        strncpy(response, "Messaggio ricevuto dal server", strlen("Messaggio ricevuto dal server"));
        write(connection, &responce, strlen(responce)); 
        //write(connection, "Messaggio ricevuto dal server", strlen("Messaggio ricevuto dal server")); 
    }
}