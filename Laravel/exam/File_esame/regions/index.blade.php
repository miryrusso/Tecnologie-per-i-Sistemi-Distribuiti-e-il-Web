<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Indice Costumes</title>
</head>
<body>
    <h1> Elenco Region </h1>
    @foreach ($regions as $region)
        <p>Region ID : {{$region->id}}</p>
        <li>Name : {{$region->name}}</li>
        <a href = "{{route('regions.edit', $region->id)}}"><button>Modifica </button></a>
        <a href = "{{route('regions.show', $region->id)}}"><button>Elimina</button></a>
    @endforeach

    <a href = "{{route('regions.create')}}"> <button> Inserisci una nuova Region </button></a>
</body>
</html>
