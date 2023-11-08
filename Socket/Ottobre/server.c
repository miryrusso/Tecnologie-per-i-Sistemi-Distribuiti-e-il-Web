/**
 *  4 Ottobre 2023
 * Se la stringa che è ricevuta è composta un numero arbitrario di "V" restituisce vero,
 * mentre se anche un solo carattere è diverso dalla V maiscola restituisce falso.
 * [RICORDA CHE TELNET METTE NEGLI ULTIMI DUE CARATTERI DELLA STRINGA \r\n]
*/

#include <stdio.h>
#include <stdlib.h> 
#include <arpa/inet.h>
#include <string.h>

#define PORT 7775
#define IP "127.0.0.1"
#define MAX_BUFFER 1024

int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER]; 

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("[APERTURA SOCKET]"); 
        exit(-1); 
    }


    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT); 


    if((bind(s, (struct sockaddr *)&server, len)) < 0){
        perror("[BIND PROBLEM]"); 
        exit(-1); 
    }

    printf("Server ready..\n"); 

    if((listen(s,1)) != 0){
        perror("[LISTEN PROBLEM"); 
        exit(-1);
    }; 

    

    
    while(1){
        int conn_accept; 
        char responce[MAX_BUFFER]; 
        if((conn_accept = accept(s, (struct sockaddr *)&client, &len)) < 0 ){
            perror("Problem Accept.."); 
            exit(-1); 
        }

        int n; 
        if((n = read(conn_accept, &buffer, MAX_BUFFER)) < 0){
            perror("[READ PROBLEM]"); 
            exit(-1); 
        }

        // TELNET
        buffer[n] = '\0'; 
        printf("[STAMPO BUFFER IN RICEZIONE %s \n", buffer); 
        
        for(int i = 0 ; i< strlen(buffer) -2; i++){
            if(buffer[i] != 'V'){
                printf("Buffer[%d] = %s \n", i, buffer); 
                sprintf (responce, "Falso"); 
                break;   
            }

            sprintf (responce, "Vero"); 
        }
        


        /* PROPRIO SERVER
        printf("[STAMPO BUFFER IN RICEZIONE %s \n", buffer); 

          for(int i = 0 ; i< n - 2; i++){
            if(buffer[i] != 'V'){
                printf("Buffer[%d] = %s \n", i, buffer); 
                sprintf (responce, "Falso"); 
                break;   
            }

            sprintf (responce, "Vero"); 
        }

        responce[n-1] = '\n'; 
        responce[n] = '\0'; 


        */
        printf("BUFFER DI RISPOSTA %s \n", responce); 
        write(conn_accept, &responce, strlen(responce)); 
        memset(&responce, 0 , strlen(responce)); 
        close(conn_accept); 

    }
    close(s);
}
