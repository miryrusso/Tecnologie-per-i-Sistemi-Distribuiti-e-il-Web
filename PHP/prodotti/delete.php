<?php
    if($_SERVER["REQUEST_METHOD"] == "GET"){
        
        require_once("db_connection.php"); 
        //setto i vari campi 
        $id = mysqli_real_escape_string($db_connection, $_GET["id"]);
        
        

        $query = "DELETE FROM mySql WHERE id = $id; "; 
        mysqli_query($db_connection, $query); 
        header("Location: lista.php"); 
    }
?>