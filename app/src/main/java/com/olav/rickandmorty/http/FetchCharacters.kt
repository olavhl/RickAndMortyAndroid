package com.olav.rickandmorty.http

import android.util.Log
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.retrofit.RaMApi
import java.lang.Error
import java.lang.Exception

fun interface FetchCharacters {
    suspend operator fun invoke(): Characters?
}

fun RaMApi.fetchCharacters() = FetchCharacters {
    try {
        getCharacters().body()
    } catch (e: Error) {
        Log.e("Failed to fetch Characters from API", e.toString())
        null
    }
}