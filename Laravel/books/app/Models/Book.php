<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Book extends Model
{
    use HasFactory;
    protected $fillable = ['isbn', 'titolo', 'prezzo','author'];

    public function author(){
        return $this->belongsTo(Author::class);
    }
}
