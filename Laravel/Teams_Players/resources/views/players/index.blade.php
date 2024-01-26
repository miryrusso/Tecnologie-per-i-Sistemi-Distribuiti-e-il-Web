<html>
<body>
    <h1>Elenco dei giocatori</h1>
    @foreach ($players as $player)
        <p>Player ID: {{$player->id}}</p>
        <li>Nome: {{$player->nome}}</li>
        <li>Numero Maglia: {{$player->n_maglia}}</li>
        <li>Squadra: {{$player->team}}</li>

        <a href="{{ route('players.show', $player->id) }}"><button>Mostra</button></a>
        <a href="{{ route('players.edit', $player->id) }}"><button>Modifica</button></a>

    @endforeach

    <a href="{{ route('players.create') }}">Crea Nuovo Gioco</a>

    <br><br><a href="../"><button>Home</button></a>
</body>
</html>
