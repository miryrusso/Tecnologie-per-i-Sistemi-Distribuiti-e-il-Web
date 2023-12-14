<?php


//Controllo se la richiesta GET e se esiste ID

//controllo la tipologia di richiesta

if($_SERVER["REQUEST_METHOD"] == "GET"){

    //controllo che esista ID
    if($_GET["id"]){
        require_once("db_connection.php");
        $id = mysqli_real_escape_string($db_connection, $_GET["id"]);

    //Ora elimino il libro
        $query = "DELETE FROM books WHERE id = $id ;";
        $result = mysqli_query($db_connection, $query);

        header("Location: books.php");
    }


    
    
}

?>