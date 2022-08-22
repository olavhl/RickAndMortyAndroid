package com.olav.rickandmorty.retrofit.episode

import com.olav.rickandmorty.model.Episodes
import retrofit2.Response
import retrofit2.http.GET

interface EpisodeApi {
    @GET("/api/episode")
    suspend fun getEpisodes(): Response<Episodes>
}