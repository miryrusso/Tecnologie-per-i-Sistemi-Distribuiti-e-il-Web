<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista Articoli</title>
</head>
<body>

    <% 
    package edu.unict.magazzon;
    import java.sql.ResultSet;
    ResultSet products = (ResultSet) request.getAttribute("products"); 
    %> 

    <table>
        <tr>
            <th>Nome Prodotto</th>
            <th>Giacenza</th>
            <th>Prezzo</th>
        </tr>

        
    </table>
    
</body>
</html>