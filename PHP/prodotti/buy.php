
<?php
//aggiungere alla lista (punto 1.2) un link o un bottone "compra" che permette di acquistare il prodotto. 
//Una volta acquistato,  la giacenza del prodotto verrà decrementata.

    require_once("db_connection.php"); 
    if($_GET["id"]){
            
        $id = mysqli_real_escape_string($db_connection, $_GET["id"]); 
        $query = "UPDATE mySql SET giacenza = giacenza - 1 WHERE id = $id;"; 
        $result = mysqli_query($db_connection, $query);
       
        header("Location: lista.php");  

    }else{
        header("Location: lista.php"); 
    }
?>