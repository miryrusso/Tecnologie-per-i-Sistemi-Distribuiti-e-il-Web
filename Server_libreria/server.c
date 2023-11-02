/**
 * Realizzare un server che tiene traccia della disponibilità di 10 libri, che possono essere disponibili o in prestito;
	riceve delle richieste da parte dei client del tipo "titolo del libro"
	e risponde "Disponibile", "In prestito" o "Inesistente" a seconda del titolo richiesto.

*/



#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>

#define PORT 7773
#define IP "127.0.0.1"
#define MAX_BUFFER 1024
#define MAX_BOOKS 10


//struttura che tiene traccia dei libri 
typedef struct
{
    char title[40]; 
    int disponibile; 
}Book;

void inizialize_book(Book books[MAX_BOOKS]){
    for(int i = 0; i<MAX_BOOKS; i++){
        sprintf(books[i].title, "Libro%d\r\n", i+1);
        printf("%s \n", books[i].title); 
        books[i].disponibile = 1; //inizialmente sono tutti disponibili. 
    }
}

int main(int argc, char **argv){
    int s; 
    struct sockaddr_in server, client; 
    socklen_t len = sizeof(server); 
    char buffer[MAX_BUFFER]; 

    Book books[MAX_BOOKS]; 
    inizialize_book(books); 

    //APERTURA DELLA SOCKET 
    if((s = socket(AF_INET, SOCK_STREAM, 0))<0){
        perror("Inizializzazione socket...\n"); 
        exit(-1); 
    }

    printf("Server ready...\n"); 

    memset(&server, 0, len); 
    server.sin_family = AF_INET; 
    server.sin_addr.s_addr = INADDR_ANY; 
    server.sin_port = htons(PORT); 

    if((bind(s, (struct sockaddr *)&server, len)) < 0){
        perror("Errore nella bind ... \n"); 
        exit(-1); 
    }


    //listen 
    if(listen(s, 1) != 0){
        perror("Listern problem...\n"); 
        exit(-1); 
    }


    int connectionSocket; 
    if((connectionSocket = accept(s, (struct sockaddr *)&client, &len)) < 0){
        perror("Accept problem...\n"); 
        exit(-1); 
    }


    while(1){
        int n; 
        if((n = read(connectionSocket, &buffer, MAX_BUFFER)) < 0){
            perror("Read problem... \n"); 
            exit(-1); 
        }

        //punto A
        printf("[MESSAGE TEXT] %s \n", buffer); 
        buffer[n] = '\0';
        printf("Qui ci sono??? \n");

        for(int i = 0; i< MAX_BOOKS; i++){
            printf("Qui ci sono??? P2 \n");

            if(strcmp(books[i].title, buffer) == 0){
                printf("Abbiamo trovato il libro..\n ");
                if(books[i].disponibile){
                    books[i].disponibile = 0; 
                    sprintf(buffer, "Disponibile \n"); 
                } else { 
                    sprintf(buffer, "In prestito... \n"); 
                }
                break; 
                
            }else{
                printf("Non abbiamo trovato il libro..\n ");
                sprintf(buffer, "Inesistente \n");
            }
        }

        //punto B 
        write(connectionSocket, &buffer, strlen(buffer));        
    }

    close(s); 
}