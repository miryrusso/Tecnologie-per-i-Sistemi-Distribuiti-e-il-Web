<?php

require_once("db_connection.php");


//Ora prendo tutti i libri dentro la tabella
$query = "SELECT * FROM books";
$result = mysqli_query($db_connection, $query);
$books = [];


if (mysqli_num_rows($result) > 0) {
    // per ogni riga conservo il libro
    $count = 0;
    while($row = mysqli_fetch_assoc($result)) {
        $books[$count] = $row;
        $count = $count+1;
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
            <h2>Lista Libri</h2>
            <table>
            <tr>
                <th>ID</th>
                <th>ISBN</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publisher</th>
            </tr>

            <?php 
                foreach ($books as $book) {
            ?>

                <tr>
                    <td><?php echo $book["id"]; ?></td>
                    <td><?php echo $book["isbn"]; ?></td>
                    <td><?php echo $book["title"]; ?></td>
                    <td><?php echo $book["author"]; ?></td>
                    <td><?php echo $book["publisher"]; ?></td>
                    <td><a href="update_book.php?id=<?php echo $book["id"]; ?>">Aggiorna Libro</a></td>
                    <td><a href="delete_book.php?id=<?php echo $book["id"]; ?>">Elimina Libro</a></td>
                </tr>

                <?php } ?>

            </table>
        </div>
    </body>
</html> 