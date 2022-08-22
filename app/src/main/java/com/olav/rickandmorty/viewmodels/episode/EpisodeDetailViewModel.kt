package com.olav.rickandmorty.viewmodels.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.http.characters.FetchCharacter
import com.olav.rickandmorty.http.episodes.FetchEpisode
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.model.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodeDetailViewModel(
    private val fetchEpisode: FetchEpisode,
    private val fetchCharacter: FetchCharacter,
) : ViewModel() {
    val episode = MutableLiveData<Episode?>()

    private val _characters = MutableLiveData<Characters?>()
    val characters = _characters

    fun getEpisode(id: String) {
        viewModelScope.launch {
            episode.value = withContext(Dispatchers.IO) { fetchEpisode(id) }
        }
    }

    fun getCharacters() {
        var tempCharacters: Characters? = null

        viewModelScope.launch {
            episode.value?.characters?.forEach { characterUrl ->
                val characterId = characterUrl.split("/").last()
                val fetchedCharacter = withContext(Dispatchers.IO) { fetchCharacter(characterId) }

                if (fetchedCharacter != null) {
                    if (tempCharacters != null) {
                        tempCharacters?.results?.add(fetchedCharacter)
                    } else {
                        tempCharacters = Characters(results = mutableListOf(fetchedCharacter))
                    }
                }
            }
            _characters.value = tempCharacters
        }
    }
}

