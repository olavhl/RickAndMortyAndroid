package com.olav.rickandmorty.http.episodes

import android.util.Log
import com.olav.rickandmorty.model.Episodes
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import java.lang.Error

fun interface FetchEpisodes {
    suspend operator fun invoke(): Episodes?
}

fun EpisodeApi.fetchEpisodes() = FetchEpisodes {
    try {
        getEpisodes().body()
    } catch (e: Error) {
        Log.e("Failed to fetch Episodes from API", e.toString())
        null
    }
}