/**
 * Scrivi un programma C che implementi un server di socket. Il server deve ascoltare sulla porta 7777 e gestire le seguenti operazioni:

list: Se il client invia una richiesta LIST, il server deve restituire all'utente una lista di elementi memorizzati in una coda.
Aggiunta: Se il client invia una richiesta di aggiunta, il server deve inserire l'elemento nella coda. Se l'elemento è già presente nella coda, il server deve rispondere "presente". Se l'elemento non è già presente, il server deve rispondere "inserita".
Scrivi una funzione trim per pulire una stringa da caratteri non alfanumerici.

Scrivi una funzione list che invii una lista di elementi al client.

Scrivi una funzione exist che verifica se un elemento è già presente in una coda.

Scrivi una funzione add_req che aggiunge un elemento a una coda. Se l'elemento è già presente, la funzione deve restituire 2. Se l'elemento viene inserito correttamente, la funzione deve restituire 0.

Gestisci le connessioni dei client in modo concorrente.

Assicurati di gestire errori e eccezioni in modo adeguato.
*/

#include <stdio.h>
#include <stdlib.h> 
#include <arpa/inet.h>
#include <string.h>

#define MAX_BUFFER 1024
#define PORT 7777
#define ADDRESS "127.0.0.1"

//funzione trim
void trim(char *bf){
    int j = 0; 
    for(int i = 0; i< strlen(bf); i++){
        if(isalnum(bf[i])){
            bf[j] = bf[i]; 
            j++;
        }
    }

    bf[j] = '\0'; 
}

//Scrivi una funzione list che invii una lista di elementi al client.
void list(char *bf){

}

int main(int argc, char **argv){
    int s; 
    char buffer[MAX_BUFFER];
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    
    if((s = socket(AF_INET, SOCK_STREAM, 0)) <0){
        perror("Apertura socket\n"); 
        exit(-1); 
    }

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT); 


    if((bind(s, (struct sockaddr *)&server, len)) < 0 ){
        perror("Build error..\n"); 
        exit(-1); 
    }


    if((listen(s,1)) != 0){
        perror("Listen error..\n"); 
        exit(-1); 
    }


    int connectionSocket; 
    if((connectionSocket = accept(s, (struct sockaddr *)&client, &len)) <0){
        perror("Accept error..\n"); 
        exit(-1); 
    }


    while(1){
        int n; 
        if((n = read(connectionSocket, &buffer, MAX_BUFFER))< 0){
            perror("Read..\n"); 
            exit(-1);
        }
        buffer[n] = '\0';

        if(strncmp(buffer, "LIST", strlen("LIST")) == 0){
            //richiama list
        }else{
            //aggiungi alla coda
        }

        printf("[BUFFER TEXT] %s\n", buffer); 
        


        write(connectionSocket, &buffer, strlen(buffer)); 
    }

}
