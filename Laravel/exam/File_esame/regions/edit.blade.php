<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Modifica i campi </h1>
    <form action = "{{route('regions.update', $region->id)}}" method = "post">
        @csrf
        @method("put")
        Name : <input type = "text" name = "name" value = "{{$region->name}}">
        <button type = "submit"> Aggiorna </button>
    </form>
</body>
</html>
