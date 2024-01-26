<html>
    <body>
        <h1>Dettagli Giocatore</h1>

        <p>ID: {{ $player->id }}</p>
        <p>Nome: {{ $player->nome }}</p>
        <p>Numero di maglia: {{ $player->n_maglia }}</p>
        <p>Squadra: {{ $player->team }}</p>

        <form action="{{ route('players.destroy', $player->id) }}" method="POST">
            @csrf
            @method('delete')
            <button type="submit">Elimina</button>
        </form><br>

        <br><a href="{{ route('players.index') }}"><button>Torna all'elenco</button></a>
    </body>
</html>
