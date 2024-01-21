<html>
    <body>
        <h1>Inserisci un nuovo libro</h1>
        <form action = "{{route('books.store')}}" method = "POST">
            @csrf
            <input type= "text" name = "isbn" placeholder="Inserisci isbn">
            <input type= "text" name = "titolo" placeholder="Inserisci titolo">
            <input type= "text" name = "prezzo" placeholder="Inserisci prezzo">

            <select name="author" >
                @foreach ($authors as $author )
                    <option value = "{{$author->id}}">{{$author->nome}}</option>
                @endforeach
            </select>
            <button type = "submit"> Invia Risultati </button>
        </form>
    </body>
<html>
