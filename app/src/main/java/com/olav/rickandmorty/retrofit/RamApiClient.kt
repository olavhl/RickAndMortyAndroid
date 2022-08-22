package com.olav.rickandmorty.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RamApiClient {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .client(client)
        .baseUrl("https://rickandmortyapi.com")
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}