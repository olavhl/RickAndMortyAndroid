package com.olav.rickandmorty.viewmodels.episode

import androidx.lifecycle.LiveData
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
    private val _episodes = MutableLiveData<Episodes?>()
    val episodes: LiveData<Episodes?> = _episodes

    fun loadEpisodes() {
        viewModelScope.launch {
            _episodes.value = withContext(Dispatchers.IO) { fetchEpisodes() }
        }
    }
}

