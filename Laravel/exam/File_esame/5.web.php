<?php
namespace App\Http\Controllers;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('index');
});

Route::resource("costumes", CostumeController::class);

Route::resource("regions", RegionController::class);

Route::delete('/costumes', [CostumeController::class, 'destroyAll'])-> name('costumes.destroyAll');


Route::put('/costumes', [CostumeController::class, 'dimezzaPrezzi'])->name('costumes.dimezzaPrezzi');
