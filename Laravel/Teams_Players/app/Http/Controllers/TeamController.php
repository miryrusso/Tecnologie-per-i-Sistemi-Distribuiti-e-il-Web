<?php

namespace App\Http\Controllers;

use App\Models\Team;
use Illuminate\Http\Request;

class TeamController extends Controller
{
    public function index()
    {
        return view('teams.index', ["teams" => Team::all()]);
    }

    public function create()
    {
        return view('teams.create');
    }

    public function store(Request $request)
    {
        Team::create($request->all());
        return redirect()->route('teams.index');
    }

    public function show(Team $team)
    {
        return view('teams.show', ['team' => $team]);
    }

    public function edit(Team $team)
    {
        return view('teams.edit', ['team' => $team]);
    }

    public function update(Request $request, Team $team)
    {
        $team->update($request->all());
        return redirect()->route('teams.index');
    }

    public function destroy(Team $team)
    {
        $team->delete();
        return redirect()->route('teams.index');
    }
}
