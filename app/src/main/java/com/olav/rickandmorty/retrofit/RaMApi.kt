package com.olav.rickandmorty.retrofit

import com.olav.rickandmorty.model.Characters
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RaMApi {
    @GET("/api/character")
    suspend fun getCharacters(): Response<Characters>
}