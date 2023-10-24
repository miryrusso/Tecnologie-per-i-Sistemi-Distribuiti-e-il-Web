#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <ctype.h>

#define PORT 7777
#define MAX_SIZE 1024
typedef enum{
    FALSE,
    TRUE
}bool;

bool stato = FALSE;
int numero; 
int conto[10] = {0}; 

void controllo(char *buffer){
    //leggi la prima lettera del buffer
    char *somma_versata; 
    switch (buffer[0])
    {
    case 'U':
        if (buffer[1] != '\0' && isdigit(buffer[1])) {
            numero = buffer[1] - '0';
            sprintf(buffer, "Sei entrato nel conto numero %d" ,numero);
            stato = TRUE;
        }
        break;
    
    case 'V': 
        if(stato){
            strtok(buffer, buffer[0]); 
            somma_versata = strtok(NULL, buffer[4]);
            if(isdigit(somma_versata)){
                //continue
            }
        }
    
    default:
        sprintf(buffer, "Carattere non riconosciuto..\n"); 
        break;
    }        
}

int main(int argc, char **argv){
    //array di 10 interi 
     
    char buffer[MAX_SIZE]; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(client); 
    int s; 
    int connection; 

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("Socket Open"); 
        exit(-1);
    }

    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT);

    if(bind(s, (struct sockaddr *)&server, sizeof(server)) < 0){
        perror("Bind \n");
        exit(-1);
    }


    printf("Server ready.. \n"); 

    if(listen(s, 1)!= 0){
        perror("Listen.. \n"); 
        exit(1); 
    }

    if(connection = accept(s, (struct sockaddr*)&client, len) < 0){
        perror("Accept error..."); 
        exit(-1);
    }

    for(;;){
        int retcode; 
        if((retcode = read(s, &buffer, MAX_SIZE)) < 0){
            perror("Read..\n"); 
            exit(-1); 
        }

        buffer[retcode] = '\0'; 
        printf('%s', buffer); 

        //inserisci funzione controlla
        controlla(buffer); 

    }
    
}