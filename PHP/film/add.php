<?php
    require_once("db_connection.php"); 
    if(isset($_POST["titolo"]) && isset($_POST["regista"])){
        $titolo = $db_connection->real_escape_string($_POST["titolo"]); 
        $regista = $db_connection->real_escape_string($_POST["regista"]); 
        $query = "INSERT INTO wlist (titolo,regista) VALUES ('$titolo','$regista');";
        $result = $db_connection->query($query); 
      
       
        header("Location: index.php"); 
    }
?>