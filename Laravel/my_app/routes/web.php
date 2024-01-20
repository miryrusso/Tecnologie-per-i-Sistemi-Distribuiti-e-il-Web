<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome'); //nome del file in view
});


Route::get('/home', function () {
    return "<h1>Ciao</h1>";
});


//scrivere in formato Json
Route::get('/giada', function () {
    return (['nome'=>'Giada', 'cognome' =>  'Mamma']);
});
//blade si trova dentro la cartella views e contiene un file dove inserire css o javascript

Route::get('/contact', function () {
    return view('contact'); //nome del file in view
});

Route::get('/about', function () {
    return view('about'); //nome del file in view
});

//le view possono essere parametriche
