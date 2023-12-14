<?php


//Se il file viene richiesto tramite GET allora mostro il FORM, altrimenti se è una POST inserisco il nuovo libro e rimando l'utente
//alla pgina dei libri


//controllo la tipologia di richiesta

if($_SERVER["REQUEST_METHOD"] == "POST"){


    require_once("db_connection.php");
    $isbn = mysqli_real_escape_string($db_connection, $_POST["isbn"]);
    $title = mysqli_real_escape_string($db_connection, $_POST["title"]);
    $author = mysqli_real_escape_string($db_connection, $_POST["author"]);
    $publisher = mysqli_real_escape_string($db_connection, $_POST["publisher"]);
    $price = floatval($_POST["price"]);

    //Ora prendo tutti i libri dentro la tabella
    $query = "INSERT INTO books (isbn, title, author, publisher, price) VALUES ('$isbn','$title','$author','$publisher','$price');";
    $result = mysqli_query($db_connection, $query);

    header("Location: books.php");
    
}

?>

<!DOCTYPE html>
<html>
    <head>
        <title>Books</title>
    </head>
    <body>
        <div>
            <h2>Inserisci nuovo Libro</h2>
            <form method="POST">
                <label>ISBN</label>
                <input type="text" name="isbn" placeholder="Inserisci ISBN" />
                <br>

                <label>Titolo</label>
                <input type="text" name="title" placeholder="Inserisci Titolo" />
                <br>

                <label>Autore</label>
                <input type="text" name="author" placeholder="Inserisci Autore" />
                <br/>

                <label>Publisher</label>
                <input type="text" name="publisher" placeholder="Inserisci Publisher" />
                <br>

                <label>Prezzo</label>
                <input type="text" name="price" placeholder="Inserisci Prezzo" />
                <br>
                <br>

                <button>Inserisci Libro</button>
            </form>
            
        </div>
    </body>
</html> 