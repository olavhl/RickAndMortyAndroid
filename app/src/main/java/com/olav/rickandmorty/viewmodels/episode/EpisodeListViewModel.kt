package com.olav.rickandmorty.viewmodels.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.http.episodes.FetchEpisodes
import com.olav.rickandmorty.model.Episodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

