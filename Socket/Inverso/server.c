#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>

#define IP "127.0.0.1"
#define PORT 5530
#define MAX_BUFFER 1024

int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER];
    int check = 1; 

    if((s = socket(AF_INET, SOCK_STREAM, 0))<0){
        perror("Inizializzazione socket...\n"); 
        exit(-1); 
    }

    printf("Server ready...\n"); 

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT);

    if((setsockopt(s,SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

       if((bind(s, (struct sockaddr *)&server, len)) < 0){
        perror("Errore nella bind ... \n"); 
        exit(-1); 
    }

    if((listen(s,1)) != 0){
        perror("Listen Error..\n"); 
        exit(-1); 
    }


    int connect; 
    if((connect = accept(s, (struct sockaddr *)&client, &len)) < 0){
        perror("accept Error..\n"); 
        exit(-1); 
    }

    printf("Connessione accettata..\n"); 

    while(1){
        int n; 
        //char responce[MAX_BUFFER]; 

        if((n = read(connect, &buffer, MAX_BUFFER)) <0){
            perror("Read Error..\n"); 
            exit(-1); 
        }
        printf("Messaggio ricevuto..\n");

        /*
        buffer[n] = '\0'; 

        int lunghezza = strnlen(buffer, MAX_BUFFER); 

        for(int i = 0; i< lunghezza; i++){
            responce[i] = buffer[lunghezza -1 - i];
        } 
        */
        

        //buffer[n] = '\0';

        char responce[n+1];
        for(int i = n-2; i >= 0; i--){
            responce[n - i -2] = buffer[i];
        }
        responce[n-1]='\n';
        responce[n]='\0';

        printf("Stringa convertita: %s\n", responce); 

        write(connect, &responce, sizeof(responce)); 
    }

    close(s); 
}