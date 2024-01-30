<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Regions</title>
</head>
<body>
    <h1>Aggiungi una nuova Region</h1>
    <form action = "{{route('regions.store')}}" method="post">
        
        @csrf
        Name : <input type = "text" name = "name" placeholder="Inserisci Nome Region">
        <button type = "submit"> Invia </button>
    </form>
</body>
</html>
