<html>
    <body>
        <h1> Elenco dei Teams </h1>
        @foreach ($teams as $team)
            <p>Teams ID: {{$team->id}}</p>
            <li>Nome: {{$team->nome}}</li>
            <li>Stadio: {{$team->stadio}}</li>
            <li>Anno Fondazione: {{$team->anno_fondazione}}</li>
            <a href = "{{ route('teams.show', $team->id)}}">Mostra</a>
            <a href = "{{ route('teams.edit', $team->id)}}">Modificad</a>
        @endforeach

        <a href="{{ route('teams.create') }}"><button> Nuova Squadra</button></a>
        <br><br><a href="../"><button>Home</button></a>
    </body>
</html>
