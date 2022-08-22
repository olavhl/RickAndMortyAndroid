package com.olav.rickandmorty.viewmodels.episode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Error

class EpisodeDetailViewModel(
    private val fetchEpisode: FetchEpisode,
): ViewModel() {
    val episode = MutableLiveData<Episode?>()

    fun getEpisode(id: String) {
        viewModelScope.launch {
            episode.value = withContext(Dispatchers.IO) { fetchEpisode(id) }
        }
    }
}

fun interface FetchEpisode {
    suspend operator fun invoke(id: String) : Episode?
}

fun EpisodeApi.fetchEpisode() = FetchEpisode {
    try {
        getEpisode(it).body()
    } catch (e: Error) {
        Log.e("Failed to fetch Episode from API", e.toString())
        null
    }
}