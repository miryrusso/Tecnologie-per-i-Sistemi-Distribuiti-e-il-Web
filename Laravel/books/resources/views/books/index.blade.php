<html>
    <h1>Elenco dei libri </h1>
    @foreach($books as $book)
        <p>Book ID : {{$book->id}}</p>
        <li>ISBN : {{$book->isbn}}</li>
        <li>Titolo : {{$book->titolo}}</li>
        <li>Prezzo : {{$book->prezzo}}</li>
        <li>Autore : {{$book->author}}</li>

        <a href = "{{ route('books.edit', $book->id)}}"><button>Modifica</button></a>
        <a href = "{{ route('books.show', $book->id)}}"><button>Mostra</button></a>
    @endforeach
    <br>
    <a href = "{{route('books.create')}}"><button>Crea un nuovo libro amico!</button></a>
    <br>
    <a href = "../"><button>Torna alla home</button></a>
</html>
