<?php
namespace App\Http\Controllers;
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
    return view('index');
});

Route::resource("costumes", CostumeController::class);

Route::resource("regions", RegionController::class);

Route::delete('/costumes', [CostumeController::class, 'destroyAll'])-> name('costumes.destroyAll');


Route::put('/costumes', [CostumeController::class, 'dimezzaPrezzi'])->name('costumes.dimezzaPrezzi');
