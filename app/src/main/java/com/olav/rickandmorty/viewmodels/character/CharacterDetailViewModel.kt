package com.olav.rickandmorty.viewmodels.character

import androidx.lifecycle.LiveData
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
    private val _character = MutableLiveData<Character?>()
    val character: LiveData<Character?> = _character

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            _character.value = withContext(Dispatchers.IO) { fetchCharacter(id) }
        }
    }
}