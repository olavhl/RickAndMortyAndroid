package com.olav.rickandmorty.viewmodels.episode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.model.Episodes
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Error

class EpisodeListViewModel(
    private val fetchEpisodes: FetchEpisodes,
) : ViewModel() {
    val episodes = MutableLiveData<Episodes?>()

    fun loadEpisodes() {
        viewModelScope.launch {
            episodes.value = withContext(Dispatchers.IO) { fetchEpisodes() }
        }
    }
}

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