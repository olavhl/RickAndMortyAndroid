package com.olav.rickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.retrofit.RaMApi
import com.olav.rickandmorty.retrofit.RaMApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RaMViewModel(
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

fun interface FetchCharacters {
    suspend operator fun invoke(): Characters?
}

fun RaMApi.fetchCharacters() = FetchCharacters {
    getCharacters().body() ?: throw Exception("Failed to fetch Characters from API")
}