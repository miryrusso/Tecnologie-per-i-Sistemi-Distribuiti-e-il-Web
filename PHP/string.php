<?php
    echo strlen("Hello world!"); // outputs 12

    echo str_word_count("Hello world!"); // outputs 2

    echo strrev("Hello world!"); // outputs !dlrow olleH

    #All'interno della word del primo parametro so la posizione del 2 parametro 
    echo strpos("Hello world!", "world"); // outputs 6

    #Sostituisce il primo argomento con il secondo argomento all'interno della frase contenuta nel terzo argomento 
    #Si sostituiscono tutte le occorrenze
    #Posso passare un quarto parametro come riferimento &var che mi conta quante occorrenze ho modificato 
    #(ATTENZIONE DEVE DICHIARARE LA VARIABILE ALL'INIZIO)

    $ciao = 1; 
    echo str_replace("world", "Dolly", "Hello world!", $ciao); // outputs Hello Dolly!
    echo "$ciao";

?>