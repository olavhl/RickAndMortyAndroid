package com.olav.rickandmorty.http.episodes

import android.util.Log
import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import java.lang.Error

fun interface FetchEpisode {
    suspend operator fun invoke(id: String): Episode?
}

fun EpisodeApi.fetchEpisode() = FetchEpisode {
    try {
        getEpisode(it).body()
    } catch (e: Error) {
        Log.e("Failed to fetch Episode from API", e.toString())
        null
    }
}