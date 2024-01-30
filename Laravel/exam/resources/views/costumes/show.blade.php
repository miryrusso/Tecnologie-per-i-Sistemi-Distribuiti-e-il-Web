<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form action = "{{route('costumes.destroy', $costume->id)}}" method = "POST">
        @csrf
        @method("DELETE")
        <p>Costume ID : {{$costume->id}}</p>
        <li>Name : {{$costume->name}}</li>
        <li>Job : {{$costume->job}}</li>
        <li>Img : {{$costume->img}}</li>
        <li>Price : {{$costume->price}}</li>
        <li>Id Region : {{$costume->id_region}}</li>

        <button type = "submit"> Elimina </button>
    </form>
</body>
</html>
