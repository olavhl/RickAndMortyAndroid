package com.olav.rickandmorty.retrofit.episode

import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.model.Episodes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    @GET("/api/episode")
    suspend fun getEpisodes(): Response<Episodes>

    @GET("/api/episode/{id}")
    suspend fun getEpisode(@Path("id") id: String): Response<Episode>
}