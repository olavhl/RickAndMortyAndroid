package com.olav.rickandmorty.http

import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.retrofit.RaMApi
import java.lang.Exception

fun interface FetchCharacters {
    suspend operator fun invoke(): Characters?
}

fun RaMApi.fetchCharacters() = FetchCharacters {
    getCharacters().body() ?: throw Exception("Failed to fetch Characters from API")
}