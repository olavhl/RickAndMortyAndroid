package com.olav.rickandmorty.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.retrofit.RaMApi
import com.olav.rickandmorty.retrofit.RaMApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaMViewModel: ViewModel()  {
    // API
    private val raMApiClient = RaMApiClient.buildService(RaMApi::class.java)
    private val call = raMApiClient.getCharacters()

    // StateFlow
    private var _stateFlow = MutableStateFlow<Characters?>(null)
    val stateFlow = _stateFlow.asStateFlow()

    fun loadCharacters() {
        call.enqueue(object: Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.isSuccessful) {
                    _stateFlow.value = response.body()
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                print("Failed to fetch")
            }

        })
    }
}