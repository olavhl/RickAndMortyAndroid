package com.olav.rickandmorty.retrofit.character

import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.model.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("/api/character")
    suspend fun getCharacters(): Response<Characters>

    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") id: String): Response<Character>
}