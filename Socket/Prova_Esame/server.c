#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>

#define ADDR "127.0.0.1"
#define PORT 3332
#define MAX_BUFFER 1024

int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER]; 

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("Apertura socket \n"); 
        exit(-1);
    }

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT); 

    if((bind(s, (struct sockaddr *)&server, len))< 0){
        perror("Errore nella bind..");
        exit(-1); 
    }

    printf("Server ready...\n"); 

    if((listen(s,1)) != 0){
        perror("Listen problem..."); 
        exit(-1); 
    }

    while(1){
        printf("Connection start..\n");
        int connection; 
        if((connection = accept(s, (struct sockaddr *)&client, &len)) <0){
            perror("Problem accept "); 
            exit(-1); 
        }
        

        printf("Read start..\n");
        int n; 
        if((n = read(connection, &buffer, MAX_BUFFER)) <0){
            perror("Read \n"); 
            exit(-1); 
        }

        buffer[n] = '\0'; 

        printf("[STRINGA RICEVUTA DAL CLIENT] %s \n", buffer);
        close(connection); 
    }

    close(s); 
}