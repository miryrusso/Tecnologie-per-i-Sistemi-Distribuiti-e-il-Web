/**
 * Compilazione: gcc client.c -o client
 * Esecuzione Programma: ./client
*/
#include <stdio.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>

#define MAX_BUFFER 1024
#define PORT 7774
#define IP "127.0.0.1"

int main(int argc, char **argv){
    char buffer[MAX_BUFFER]; 
    struct sockaddr_in server; 
    socklen_t len = sizeof(server); 
    int s;
    int retcode;  

    if((s = socket(AF_INET, SOCK_STREAM, 0) )< 0){
        perror("Error...\n "); 
        exit(-1); 
    }

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    //server.sin_addr.s_addr = IP;
    //inet_pton(AF_INET, IP, &server.sin_addr); 
    server.sin_addr.s_addr = inet_addr(IP);
    server.sin_port = htons(PORT); 
    int prova = connect(s, (struct sockaddr *)&server, len);

    printf("%d\n", prova); 

    /*
    if((connect(s, (struct sockaddr *)&server, len)) < 0){
        perror("Connect failed...\n"); 
        exit(-1); 
    }*/

    printf("Finish connection Server...\n"); 
    
    while(1){
        printf("Seleziona l'operazione... \n");
        scanf("%s", buffer); 

        if((retcode = write(s, buffer, strlen(buffer))) < 0){
            perror("write..\n");
            exit(-1);
        }

        printf("Messaggio inviato..\n");

        if((retcode = read(s, buffer, MAX_BUFFER))<0){
            perror("Read..\n");
            exit(-1); 
        }

        buffer[retcode] = '\0'; 

        printf("%s" , buffer); 


    }

    close(s); 

}