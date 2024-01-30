<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Costumes</title>
</head>
<body>
    <h1>Aggiungi un nuovo Costume</h1>
    <form action = "{{route('costumes.store')}}" method="post">

        @csrf
        Name : <input type = "text" name = "name" placeholder="Inserisci Nome">
        Job : <input type = "text" name = "job" placeholder="Inserisci Job">
        Img : <input type = "text" name = "img" placeholder="Inserisci Img">
        Price : <input type = "number" name = "price" placeholder="Inserisci Price">
        <select name = id_region>
            @foreach ($regions as $region)
                <option value = {{$region->id}}>{{$region->name}}</option>
            @endforeach
        </select>
        <button type = "submit"> Invia </button>

    </form>

</body>
</html>
