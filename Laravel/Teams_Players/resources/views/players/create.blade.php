<html>
    <body>
        <h1>Crea Giocatore</h1>

        <form action="{{ route('players.store') }}" method="POST">
            @csrf

            Nome:<input type="text" name="nome" required>
            Numero della maglia:<input type="text" name="n_maglia" required>
            Squadra:
            <select name="team" required>
                @foreach($teams as $team)
                    <option value="{{ $team->id }}">{{ $team->nome }}</option>
                @endforeach
            </select>
            <button type="submit">Crea Giocatore</button>
        </form>
    </body>
</html>
