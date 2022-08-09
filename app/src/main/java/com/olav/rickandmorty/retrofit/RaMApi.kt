package com.olav.rickandmorty.retrofit

import com.olav.rickandmorty.model.Characters
import retrofit2.Call
import retrofit2.http.GET

interface RaMApi {
    @GET("/api/character")
    fun getCharacters(): Call<Characters>
}