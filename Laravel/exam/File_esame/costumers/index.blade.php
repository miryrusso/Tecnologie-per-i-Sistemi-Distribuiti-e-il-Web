<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Indice Costumes</title>
</head>
<body>
    <h1> Elenco Costumi </h1>
    @foreach ($costumes as $costume)
        <p>Costume ID : {{$costume->id}}</p>
        <li>Name : {{$costume->name}}</li>
        <li>Job : {{$costume->job}}</li>
        <li>Img : {{$costume->img}}</li>
        <li>Price : {{$costume->price}}</li>
        <li>Id Region : {{$costume->id_region}}</li>

        <a href = "{{route('costumes.edit', $costume->id)}}"><button>Modifica </button></a>
        <a href = "{{route('costumes.show', $costume->id)}}"><button>Mostra Dettagli </button></a>
    @endforeach

    <a href = "{{route('costumes.create')}}"> <button> Inserisci un nuovo Costume </button></a>
    <form action = "{{ route('costumes.destroyAll') }}" method = "post">
        @csrf
        @method('DELETE')
        <button>Elimina tutti i campi</button>
    </form>


    <form action="{{ route('costumes.dimezzaPrezzi') }}" method="post">
        @csrf
        @method('put')
        <button type="submit">Dimezza i prezzi</button>
    </form>


</body>
</html>
