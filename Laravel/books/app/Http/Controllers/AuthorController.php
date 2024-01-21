<?php

namespace App\Http\Controllers;

use App\Models\Author;
use Illuminate\Http\Request;

class AuthorController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view("authors.index", ["authors"=>Author::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view("authors.create");
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Author::create($request->all());
        return redirect()->route("authors.index");
    }
}
