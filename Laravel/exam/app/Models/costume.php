<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class costume extends Model
{
    use HasFactory;
    protected $fillable = ['name', 'job', 'img', 'price', 'id_region'];
    public function region(){
        return $this->belongsTo();
    }
}
