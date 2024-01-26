<html>
    <body>
        <h1>Modifica Squadra</h1>

        <form action="{{ route('teams.update', $team->id) }}" method="POST">
            @csrf
            @method('PUT')

            Nome:<input type="text" name="nome" value="{{ $team->nome }}">
            Stadio:<input type="text" name="stadio" value="{{ $team->stadio }}">
            Anno di fondazione:<input type="text" name="anno_fondazione" value="{{ $team->anno_fondazione }}">
            <button type="submit">Modifica Squadra</button>
            <a href="{{ route('teams.index') }}"><button>Torna all'elenco</button></a>
        </form>
    </body>
</html>
