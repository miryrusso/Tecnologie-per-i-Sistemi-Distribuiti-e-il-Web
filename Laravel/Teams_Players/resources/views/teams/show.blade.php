<html>
    <body>
        <h1>Dettagli Squadra</h1>

        <p>ID: {{ $team->id }}</p>
        <p>Nome: {{ $team->nome }}</p>
        <p>Stadio: {{ $team->stadio}}</p>
        <p>Anno di fondazione: {{ $team->anno_fondazione}}</p>

        <form action="{{ route('teams.destroy', $team->id) }}" method="POST">
            @csrf
            @method('DELETE')
            <button type="submit">Elimina</button>
        </form>

        <a href="{{ route('teams.index') }}"><button>Torna all'elenco</button></a>
    </body>
</html>
