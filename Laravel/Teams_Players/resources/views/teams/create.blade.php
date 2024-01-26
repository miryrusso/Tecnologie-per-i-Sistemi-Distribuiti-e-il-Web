<html>
    <body>
        <h1>Inserisci un nuovo Team</h1>
        <form action = "{{ route('teams.store')}}" method = "POST">
            @csrf
            Nome : <input type = "text" name = "nome" placeholder="Inserisci nome" required>
            Stadio : <input type = "text" name = "stadio" placeholder="Inserisci Stadio" required>
            Anno Fondazione: <input type = "text" name = "anno_fondazione" placeholder="Inserisci anno di fondazione">
            <button type = "submit">Crea Squadra</button>
        </form>
    </body>
</html>
