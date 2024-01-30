<?php

namespace App\Http\Controllers;

use App\Models\costume;
use App\Models\region;
use Illuminate\Http\Request;

class CostumeController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('costumes.index', ["costumes" => costume::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('costumes.create', ["regions"=> region::all()]);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        costume::create($request->all());
        return redirect()->route('costumes.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(costume $costume)
    {
        return view('costumes.show', ["costume" => $costume]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(costume $costume)
    {
        return view('costumes.edit', ["costume"=>$costume, 'regions' => region::all()]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, costume $costume)
    {
        $costume->update($request->all());
        return redirect()->route('costumes.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(costume $costume)
    {
        $costume->delete();
        return redirect()->route('costumes.index');
    }

    public function destroyAll(){
        $costumers = costume::all();
        foreach($costumers as $costumer)
            $costumer->delete();
        return redirect()->route('costumes.index');
    }

    public function dimezzaPrezziForm()
    {
        return redirect()->route('costumes.dimezza');
    }

    public function dimezzaPrezzi()
    {
        // Recupera tutti i costumi dal modello Costume
        $costumers = costume::all();

        // Itera su ogni costume e dimezza il valore del campo 'price'
        foreach ($costumers as $costumer) {
            $costumer->price = $costumer->price / 2;
            $costumer->save(); // Salva le modifiche nel database
        }

        // Puoi anche restituire una risposta o fare altre operazioni, a seconda delle tue esigenze
        return redirect()->route('costumes.index');
    }
}
