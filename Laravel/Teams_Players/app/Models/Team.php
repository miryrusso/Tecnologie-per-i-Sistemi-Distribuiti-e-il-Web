<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Team extends Model
{
    use HasFactory;

    protected $fillable = ['nome', 'stadio', 'anno_fondazione'];

    public function players()
    {
        return $this->hasMany(Player::class);
    }
}
