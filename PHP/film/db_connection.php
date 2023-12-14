<?php
    $servername = "localhost"; 
    $username = "miry"; 
    $password = "password"; 
    $dbname = "myDB"; 

    $db_connection = new mysqli($servername, $username, $password, $dbname); 

    //controlla la connessione 
    if($db_connection->connect_error){
        die("Connection Failed ".$db_connection->connect_error); 
    }else{
        //echo("Connessione riuscita "); 
    }
?>