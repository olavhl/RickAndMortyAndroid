package com.olav.rickandmorty.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.retrofit.RaMApi
import com.olav.rickandmorty.retrofit.RaMApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaMViewModel: ViewModel()  {
    private val raMApiClient = RaMApiClient.buildService(RaMApi::class.java)
    private val call = raMApiClient.getCharacters()

    fun loadCharacters() {
        call.enqueue(object: Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.isSuccessful) {
                    Log.i("FetchResults: ", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                print("Failed to fetch")
            }

        })
    }
}