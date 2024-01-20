//Creare un progetto su springboot
https://start.spring.io/

//DIPENDENZE DI springboot
Spring Web

//CAMBIARE PORTA 
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081

//AVVIO DI springboot
    mvn spring-boot:run 

Remember: la cartella Target si crea all'avvio di Maven

//Tutorial Magazzon
Dopo aver creato un progetto e averlo estratto andare su 
/src/main/java/resources file application.properties per inserire i dati del database

Creiamo il modello con le seguenti caratteristiche:
Fuori dalla classe:
    - @Entity 
    - @Table(name="nome_tabella")
All'interno della classe: 
    - Definire i campi della tabella 
        - @campo
        In particolare: 
            - @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
            - definiamo tutti i campi del nostro database in base al tipo
                - Esempio: private String name 
    - Creiamo i metodi get e set per ogni campo 
Creiamo view nel templates in resources
    - creiamo un file html per visualizzare i prodotti
Creiamo il controller
    -Fuori dalla Classe
        @Controller
        @RequestMapping("/filetemplate.html")
Facciamo il repository (è un'interfaccia che estende Jpa Repository)
Torniamo sul Controller
    - creiamo un riferimento final al Repository
    - @GetMapping
        -creo il metodo getProducts 
            - model.addAttribute("nome_attributo", repository.findAll()); 
Operazioni CRUD 
    -Creiamo un nuovo template
    -Creiamo un Controller utilizzando il nuovo template
    



