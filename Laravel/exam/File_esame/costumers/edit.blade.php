<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Costumes Edit</title>
</head>
<body>
    <h1> Aggiorna i Costumi </h1>
    <form action = "{{route('costumes.update', $costume->id)}}" method = "post">
        @csrf
        @method("put")
        Name : <input type = "text" name = "name" value = "{{$costume->name}}">
        Job : <input type = "text" name = "job" value = "{{$costume->job}}">
        Img : <input type = "text" name = "img" value = "{{$costume->img}}">
        Price : <input type = "number" name = "price" value = "{{$costume->price}}">
        <select name = id_region>
            @foreach ($regions as $region)
                <option value = {{$region->id}}>{{$region->name}}</option>
            @endforeach
        </select>
        <button type = "submit"> Salva </button>
    </form>
</body>
</html>
