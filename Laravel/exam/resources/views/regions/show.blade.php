<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form action = "{{route('regions.destroy',$region->id)}}" method = "POST">
        @csrf
        @method("DELETE")
        <p>Region ID : {{$region->id}}</p>
        <li>Name : {{$region->name}}</li>
        <button type = "submit"> Elimina </button>
</body>
</html>
