#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h> 

#define ADDR "127.0.0.1"
#define PORT 3333
#define MAX_BUFFER 1024

int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER]; 

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("Errore apertura socket\n"); 
        exit(-1); 
    }


    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT); 

    if((bind(s, (struct sockaddr *)&server, len))<0){
         perror("Errore Bind socket\n"); 
        exit(-1); 
    } 

    printf("Server ready..\n"); 
    //listen
    if((listen(s,1)) != 0){
        perror("Errore listen socket\n"); 
        exit(-1); 
    }

    while(1){
        int connection; 
        if((connection = accept(s, (struct sockaddr *)&client, &len))<0){
             perror("Errore accept socket\n"); 
            exit(-1); 
        }

        int n; 
        if((n = read(connection, &buffer, MAX_BUFFER)) < 0){
            perror("Read Problem");
            exit(-1);
        }
        buffer[n] = '\0';
        printf("Buffer = %s \n", buffer); 

        buffer[strcspn(buffer, "\0")] = '\n'; 

        write(connection, &buffer, strlen(buffer)); 
        close(connection);
    }

    close(s); 
}