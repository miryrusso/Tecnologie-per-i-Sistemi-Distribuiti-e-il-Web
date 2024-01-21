<?php

namespace App\Http\Controllers;

use App\Models\Book;
use App\Models\Author;
use Illuminate\Http\Request;

class BookController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view("books.index", ["books"=>Book::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view("books.create", ["authors" => Author::all()]);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Book::create($request->all());
        return redirect()->route("books.index");
    }

}
