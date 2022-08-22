package com.olav.rickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.http.characters.FetchCharacters
import com.olav.rickandmorty.model.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(
    private val fetchCharacters: FetchCharacters
): ViewModel()  {
    // StateFlow
    private var _stateFlow = MutableStateFlow<Characters?>(null)
    val stateFlow = _stateFlow.asStateFlow()

    fun loadCharacters() {
        viewModelScope.launch {
            _stateFlow.value = withContext(Dispatchers.IO) {
                fetchCharacters()
            }
        }
    }
}

