<?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once("db_connection.php"); 
        //setto i vari campi 
        $nome_prodotto = mysqli_real_escape_string($db_connection, $_POST["nome_prodotto"]);
        $giacenza = intval($_POST["giacenza"]); 
        $prezzo = floatval($_POST["prezzo"]); 
        

        $query = "INSERT INTO mySql (nome_prodotto, giacenza, prezzo) VALUES ('$nome_prodotto', '$giacenza', '$prezzo'); "; 
        mysqli_query($db_connection, $query); 
        header("Location: lista.php"); 
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <h2> AGGIUNGI UN NUOVO PRODOTTO </h2> 
    </head> 
    <body> 
        <div> 
            <form method = "POST"> 
                <label> NOME PRODOTTO </label> 
                <input type = "text" name = "nome_prodotto" placeholder = "Inserisci il nome del prodotto"> 
                <br>

                <label>Giacenza</label> 
                <input type = "text" name = "giacenza" placeholder = "Inserisci giacenza"> 
                <br> 

                <label> Prezzo </label> 
                <input type = "text" name = "prezzo" placeholder = "Inserisci il prezzo"> 
                <br> 

            <button> Inserisci il Libro </button> 
            </form>
        </div> 
    </body>
</html>
