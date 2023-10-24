
<?php
#un intero in php è grande 32 bit. Per visualizzare il tipo della variabile uso la funzione var_dump
    $x = 5985;
    var_dump($x);
#Float
    $x = 10.365;
    var_dump($x);
#Boolean
    $x = true;
    $y = false;
#Array
    $cars = array("Volvo","BMW","Toyota");
    var_dump($cars);

#Una variabile non definita ha come valore NULL
    $x = "Hello world!";
    $x = null;
    var_dump($x);
?>