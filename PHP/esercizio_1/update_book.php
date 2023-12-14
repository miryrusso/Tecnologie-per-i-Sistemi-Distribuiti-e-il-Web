<?php


//Controllo se la richiesta è GET allora prendo le info del libro e le mostro all'utente
//se la richiesta è POST allora sto aggiornando

require_once("db_connection.php");

//controllo la tipologia di richiesta

if($_SERVER["REQUEST_METHOD"] == "POST"){

    $id = mysqli_real_escape_string($db_connection, $_POST["id"]);
    $isbn = mysqli_real_escape_string($db_connection, $_POST["isbn"]);
    $title = mysqli_real_escape_string($db_connection, $_POST["title"]);
    $author = mysqli_real_escape_string($db_connection, $_POST["author"]);
    $publisher = mysqli_real_escape_string($db_connection, $_POST["publisher"]);
    $price = floatval($_POST["price"]);

    //Ora prendo tutti i libri dentro la tabella
    $query = "UPDATE books SET isbn='$isbn', title='$title', author='$author', publisher='$publisher', price=$price WHERE id=$id;";
    $result = mysqli_query($db_connection, $query);



    header("Location: books.php");
    
} else {

    // allora prendo le info del libro e le mostro per aggiornarle
    //per fare ciò prendo l'ID dalla richiesta GET

    if($_GET["id"]){

        //prendo il libro con l'ID
        $id = mysqli_real_escape_string($db_connection, $_GET["id"]);

        $query = "SELECT * FROM books WHERE id = $id ;";
        $result = mysqli_query($db_connection, $query);
        $book = mysqli_fetch_assoc($result);

    } else {
        //l'utente è arrivato qua in modo strano, lo riporto indietro
        header("Location: books.php");
    }

}

?>

<!DOCTYPE html>
<html>
    <head>
        <title>Books</title>
    </head>
    <body>
        <div>
            <h2>Aggiorna Libro</h2>
            <form method="POST">
                <label>ISBN</label>
                <input type="text" name="isbn" placeholder="Inserisci ISBN" value="<?php echo $book['isbn']; ?>" />
                <br>

                <label>Titolo</label>
                <input type="text" name="title" placeholder="Inserisci Titolo" value="<?php echo $book['title']; ?>" />
                <br>

                <label>Autore</label>
                <input type="text" name="author" placeholder="Inserisci Autore" value="<?php echo $book['author']; ?>" />
                <br/>

                <label>Publisher</label>
                <input type="text" name="publisher" placeholder="Inserisci Publisher" value="<?php echo $book['publisher']; ?>" />
                <br>

                <label>Prezzo</label>
                <input type="text" name="price" placeholder="Inserisci Prezzo" value="<?php echo $book['price']; ?>" />
                <br>
                <br>
                <input type="hidden" name="id" value="<?php echo $book['id']; ?>" />

                <button>Aggiorna Libro</button>
            </form>
            
        </div>
    </body>
</html> 