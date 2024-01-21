<html>
    <body>
        <h1>Inserisci un nuovo Autore</h1>
        <form action = "{{route('authors.store')}}" method = "POST">
            @csrf
            <input type= "text" name = "nome" placeholder="Inserisci nome">
            <input type= "text" name = "cognome" placeholder="Inserisci cognome">
            <input type= "text" name = "eta" placeholder="Inserisci eta">

            <button type = "submit"> Aggiungi Autore </button>
        </form>
    </body>
<html>
