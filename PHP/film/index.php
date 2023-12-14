<?php
    require_once("db_connection.php");
    if(isset($_POST["action"]) && $_POST["action"] == "delete"){
        $query = "TRUNCATE TABLE wlist"; 
        $result = $db_connection -> query($query); 

    }
    //stampiamo il n0umero di righe 
    $query = "SELECT COUNT(*) as count FROM flist"; 
    $result = $db_connection->query($query); 
    $row = $result->fetch_assoc(); 
    //echo $row["count"];

    $numero_film = $row["count"]; 

    $random = rand(0, $numero_film-1); 

    //new query prendere 
    $query = "SELECT * FROM flist LIMIT 1 OFFSET $random; ";
    $result = $db_connection -> query($query); 
    $row = $result->fetch_assoc(); 
    //var_dump($row); 
    //echo($row["titolo"]); 
    //echo($row["regista"]); 
?>

<!DOCTYPE html>
<html>
    <body> 
        <h1> Film consigliato: </h1>
        <?php echo ($row["titolo"]) ?>

        <!--una sezione con un form con due campi input: film e regista e un pulsante submit che aprirà la
            stessa o un’altra pagina inviandole i dati del form col metodo POST.-->
        <h1> Cerca film </h1>
            <form action = "send.php" method = "POST" >
                <input type = "text"  name = "titolo" placeholder = "Inserisci il titolo " >
                <input type = "text" name = "regista" placeholder = "Inserisci il registra" >
                <br><br>
                <button> SUBMIT </button>
            </form> 
        
        <h1> WLIST </h1> 
            <?php
                $query = "SELECT * FROM wlist; "; 
                $result = $db_connection -> query($query); 
                while($row = $result -> fetch_assoc()){
                    echo $row["titolo"]." ".$row["regista"]."<br>";

                }
            ?>
        <form method = "POST"> 
            <input type = "hidden" name = "action" value = "delete">
                
            <button>Svuota la WLIST</button>  
        </form>
    </body>
</html>