<html>
    <body>
    <form action="{{ route('players.update', $player->id) }}" method="post">
        @csrf
        @method('put') <!--Simula il metodo put perchè alcuni browser hanno solo i metodi Get e POST-->

        Nome:<input type="text" name="nome" value="{{ $player->nome }}" required>
        Numero di maglia:<input type="text" name="n_maglia" value="{{ $player->n_maglia }}" required>
        Squadra:
        <select name="team" required>
            @foreach($teams as $team)
                <option value="{{ $team->id }}">{{ $team->nome }}</option>
            @endforeach
        </select>

        <button type="submit">Modifica Gioco</button>

        <a href="{{ route('players.index') }}"><button>Torna all'elenco</button></a>
    </form>
    </body>
</html>
