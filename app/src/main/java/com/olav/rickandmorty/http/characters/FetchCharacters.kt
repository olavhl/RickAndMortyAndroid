package com.olav.rickandmorty.http.characters

import android.util.Log
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.retrofit.character.CharacterApi
import java.lang.Error

fun interface
FetchCharacters {
    suspend operator fun invoke(): Characters?
}

fun CharacterApi.fetchCharacters() = FetchCharacters {
    try {
        getCharacters().body()
    } catch (e: Error) {
        Log.e("Failed to fetch Characters from API", e.toString())
        null
    }
}