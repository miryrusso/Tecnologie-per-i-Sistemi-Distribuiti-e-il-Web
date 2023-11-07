#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>

#define PORT 3330
#define IP "127.0.0.1"
#define MAX_BUFFER 1024

int sommaCifre(char *s) {
    int somma = 0;

    for (int i = 0; i < strlen(s); i++) {
        if (isdigit(s[i])) {
            somma += s[i] - '0';
        }
    }

    return somma;
}

int main(int argc, char **argv) {
    int s;
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
    int id = 0;

    //dichiaro puntatore a FILE per poter usare gli stream 

    FILE *read = NULL; 
    FILE *write = NULL; 

    if ((s = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("Errore nell'apertura della socket\n");
        exit(-1);
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    if ((bind(s, (struct sockaddr *)&server, len)) < 0) {
        perror("bind()\n");
        return -1;
    }

    if (listen(s, 5) != 0) {
        perror("listen()\n");
        return -1;
    }

    printf("Server is ready...\n");

   
    int connectionSocket;
     if ((connectionSocket = accept(s, (struct sockaddr *)&client, &len)) < 0) {
            perror("accept()\n");
            return -1;
        }

    while (1) {
        int n; 

        read = fdopen(connectionSocket, "r");
        write = fdopen(dup(connectionSocket), "w");  
            

        char buffer[MAX_BUFFER];

        /**
         * legge una riga di testo dal file stream read (che rappresenta la connessione del client) 
         * e la memorizza nel buffer buffer.
        */
        if (fgets(buffer, MAX_BUFFER, read) == NULL) {
            perror("Read problem..\n");
            exit(-1);
        }

        printf("%s\n", buffer);

        // Calcola la somma delle cifre
        int x = sommaCifre(buffer);

        // Invia la risposta al client
        fprintf(write, "%d,%d\n", id, x);
        fflush(write);

        id++;
    }

    // Chiudi la connessione con il client
    fclose(read);
    fclose(write);

    close(s);
    return 0;
}
