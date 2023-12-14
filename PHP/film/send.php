<?php
//viene eseguita una query che cerca record di flist con titolo o regista
//corrispondenti a quelli inseriti.
    require_once("db_connection.php"); 
    if(isset($_POST["titolo"]) && isset($_POST["regista"])){
        $titolo = $db_connection->real_escape_string($_POST["titolo"]); 
        $regista = $db_connection->real_escape_string($_POST["regista"]); 
        $query = "SELECT * FROM flist WHERE titolo = '$titolo' AND regista = '$regista'"; 
        $result = $db_connection->query($query); 
      
       
        
    }
?> 


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
        if($result->num_rows > 0){
    ?>
    <h1>Film richiesto</h1>
    <?php
            $row = $result->fetch_assoc(); 
            echo $row["titolo"]; 
            $query = "SELECT * FROM wlist WHERE titolo = '$titolo' AND regista = '$regista';"; 
            $result = $db_connection->query($query);
            if($result->num_rows <= 0){
               //chiedere se si voglia inserire 
               ?>
            Vuoi aggiungere MELO il film nella WLIST? 
            <form action="add.php" method = "POST">
                <input type = "hidden" name = "titolo" value = <?php echo $titolo; ?> >
                <input type = "hidden" name = "regista" value = <?php echo $regista; ?> >
                
                <button> SI </button>
            </form>   

            <form action="index.php">
                <button> NO </button>
            </form>  

            <?php
            }else{
                echo "Esiste anche nella WLIST "; 
            }
        }else{
            echo "FILM NON TROVATO"; 
        }
    ?>
    <br>
    <a href="index.php" > Torna Indietro </a>
</body>
</html>