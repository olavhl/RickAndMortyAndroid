package com.olav.rickandmorty.viewmodels.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.http.characters.FetchCharacter
import com.olav.rickandmorty.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    private val fetchCharacter: FetchCharacter,
) : ViewModel() {
    val character = MutableLiveData<Character?>()

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            character.value = withContext(Dispatchers.IO) { fetchCharacter(id) }
        }
    }
}