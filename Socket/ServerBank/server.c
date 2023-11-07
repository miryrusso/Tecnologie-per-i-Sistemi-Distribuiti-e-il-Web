/*>>> Prima parte (richiesta)

Implementare in C o Java, su localhost, un programma server Bank, 
connection-oriented, che:

  1. mantenga un array di 10 interi conto[] che 
    rappresentano il saldo dei conti correnti da 0 a 9;

  2. risponda, su localhost, port 7777 ai seguenti messaggi:

      - [Un] dove n, da 0 a 9, è il numero del conto;
            l'effetto sarà di "Usare" il conto n, cioe` rendere 
        il conto n quello attuale, cioè su cui operano 
        implicitamente gli altri comandi;

    - [Vxyzw] dove xyzw sono 4 cifre intere;
            l'effetto sarà di Versare la somma xyzw sul conto
            attuale (cioè l'ultimo selezionato con il comando [Un])

    - [Pxyzw] dove xyzw sono 4 cifre intere;
            l'effetto sarà di Prelevare la somma xyzw dal conto
            attuale (cioè l'ultimo selezionato con il comando [Un])

    - [S]
      l'effetto sarà di inviare al cliente il Saldo
         depositato sul conto attuale (cioè l'ultimo selezionato
         con il comando [Un])

    - risponda ERROR a ogni altro dato ricevuto dal cliente

>>> Seconda parte (facoltativa)
Scrivere un programma client che permetta di inviare 
comandi al server descritto. 

In alternativa, interagire col server via telnet:
$ telnet localhost 7777


    * Compilazione: gcc server.c -o server  
    * Esecuzione Programma: ./server
*/

#include<stdio.h>
#include <stdlib.h>
#include<string.h>
#include<arpa/inet.h>

#define MAXSIZE 1024
#define PORT 7774

void handle(char buffer[MAXSIZE]);

int id = -1;
int state[10]={0};
int conto[10]={0};

int main(int argc, char **argv){
    char buffer[MAXSIZE];
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
    int s;
    int check = 1;

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        return -1;
    }

    if((setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);
    
    if((bind(s, (struct sockaddr *)& server, len)) < 0){
        perror("bind()\n");
        return -1;
    }

    if(listen(s, 1) != 0){
        perror("listen()\n");
        return -1;
    }

    printf("Server is ready...\n");

    int connSocket;
    if((connSocket = accept(s, (struct sockaddr *)& client, &len)) < 0){
        perror("accept()\n");
        return -1;
    }

    while(1){
        int n;
        if((n = read(connSocket, &buffer, MAXSIZE)) < 0){
            perror("read()\n");
            return -1;
        }

        buffer[n] = '\0';

        if(n >= 1){
            handle(buffer);
        }

        write(connSocket, &buffer, strlen(buffer));
    }

}

void handle(char buffer[MAXSIZE]){
    char X = buffer[0];
    switch(X){
        case 'U':
            printf("L'utente vuole usare un account\n");
            char *s;
            s = strtok(buffer, "U");
            int id = atoi(s);
            if(state[id] == 0){
                state[id] = 1;
                sprintf(buffer, "Sei entrato nel conto di %d\n", id);
            }else{
                sprintf(buffer, "Sei già entrato nel conto di %d\n", id);
            }
            break;
        case 'V':
            printf("L'utente vuole versare su un account\n");
            if(id != -1){
                if(state[id] == 1){
                    char* s;
                    s = strtok(buffer, "V");
                    int valore = atoi(s);
                    conto[id] += valore;
                    sprintf(buffer, "Versamento nel conto di %d andato a buon fine\n", id);
                }else{
                    sprintf(buffer, "Devi prima scegliere quale conto usare \n");
                }
            }
            break;
        case 'P':
            printf("L'utente vuole prlevare da un account\n");
            if(id != -1){
                if(state[id] == 1){
                    char* s;
                    s = strtok(buffer, "P");
                    int valore = atoi(s);
                    conto[id] -= valore;
                    sprintf(buffer, "Prelievo nel conto di %d andato a buon fine\n", id);
                }else{
                    sprintf(buffer, "Devi prima scegliere quale conto usare \n");
                }
            }
            break;
        case 'S':
            printf("L'utente vuole sapere il saldo di un account\n");
            if(id != -1){
                if(state[id] == 1){
                    sprintf(buffer, "Il saldo del conto di %d è: %d\n", id, conto[id]);
                }else{
                    sprintf(buffer, "Devi prima scegliere quale conto usare \n");
                }
            }
            break;
        default:
            sprintf(buffer, "Errore! Comando non riconosciuto \0");
            break;
    }
  
}