<?php

namespace App\Http\Controllers;

use App\Models\region;
use Illuminate\Http\Request;

class RegionController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('regions.index', ["regions"=>region::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('regions.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        region::create($request->all());
        return redirect()->route('regions.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(region $region)
    {
        return view('regions.show', ['region' => $region]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(region $region)
    {
        return view('regions.edit', ['region' => $region]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, region $region)
    {
        $region->update($request->all());
        return redirect()->route('regions.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(region $region)
    {
        $region->delete();
        return redirect()->route('regions.index');
    }
}
