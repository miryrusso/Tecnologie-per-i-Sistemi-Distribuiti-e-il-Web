<html>
    <h1>Elenco degli Autori </h1>
    @foreach($authors as $author)
        <p>Author ID : {{$author->id}}</p>
        <li>Nome : {{$author->nome}}</li>
        <li>Cognome : {{$author->cognome}}</li>
        <li>Eta : {{$author->eta}}</li>


        <a href = "{{ route('authors.edit', $author->id)}}"><button>Modifica</button></a>
        <a href = "{{ route('authors.show', $author->id)}}"><button>Mostra</button></a>
    @endforeach
    <br>
    <a href = "{{route('authors.create')}}"><button>Crea un nuovo autore amico!</button></a>
    <br>
    <a href = "../"><button>Torna alla home</button></a>
</html>
