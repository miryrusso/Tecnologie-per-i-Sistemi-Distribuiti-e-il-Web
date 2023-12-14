<?php
   require_once("db_connection.php"); 
   
    $query = "SELECT * FROM mySql"; 
    $result = mysqli_query($db_connection, $query); 
    $products = []; 

    if(mysqli_num_rows($result) > 0){
        //stampo
        for($i = 0 ; $i< mysqli_num_rows($result) ; $i++){
            $row = mysqli_fetch_assoc($result); 
            $products[$i] = $row; 
        }
    }else{
        echo( "Il database è vuoto "); 
    }
?> 

<!DOCTYPE html> 
<html> 
    <head> 
        <h2> Lista dei prodotti </h2> 
        <table> 
            <tr> 
                <th> ID </th> 
                <th> nome_prodotto </th> 
                <th> giacenza </th> 
                <th> prezzo </th> 
            </tr> 
        


            <?php 
                foreach ($products as $product) {
            ?>

            <tr>
                <td><?php echo $product["id"]; ?></td>
                <td><?php echo $product["nome_prodotto"]; ?></td>
                <td><?php echo $product["giacenza"]; ?></td>
                <td><?php echo $product["prezzo"]; ?></td>
                <td><a href="buy.php?id=<?php echo $product["id"]; ?>">Compra </a></td>
                <td><a href="delete.php?id=<?php echo $product["id"]; ?>">Elimina Libro</a></td>
            </tr>
            
            <?php } ?>
        
        </table> 
    </head>
</html>