<!-- Tutte le pagine devono contenere questo file con i tre link -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Il mio Laravel</title>
</head>
<body>
    @yield('contenuto') <!--In base alla posizione di questo yield decido in quale posizione nelle n page sarà -->
    <small>
        <ul>
            <li><a href = "/contact">Contact</a></li>
            <li><a href = "/about">About</a></li>
            <li><a href = "/">Torna alla home</a></li>
        </ul>
    </small>
</body>
</html>
