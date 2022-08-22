package com.olav.rickandmorty.http.characters

import android.util.Log
import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.retrofit.character.CharacterApi

fun interface FetchCharacter {
    suspend operator fun invoke(id: String): Character?
}

fun CharacterApi.fetchCharacter() = FetchCharacter {
    try {
        getCharacter(it).body()
    } catch (e: Error) {
        Log.e("Failed to fetch Character from API.", e.toString())
        null
    }
}