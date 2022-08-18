package com.olav.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.adapter.RaMAdapter
import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.retrofit.RaMApi
import com.olav.rickandmorty.retrofit.RaMApiClient
import com.olav.rickandmorty.viewmodels.RaMViewModel
import com.olav.rickandmorty.viewmodels.fetchCharacters
import kotlinx.coroutines.flow.collectLatest
import java.io.Serializable

class MainActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        // API
        val raMApiClient = RaMApiClient.buildService(RaMApi::class.java)

        val ramViewModel = RaMViewModel(raMApiClient.fetchCharacters())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ramViewModel.loadCharacters()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        lifecycleScope.launchWhenCreated {
            recyclerView.apply {
                ramViewModel.stateFlow.collectLatest {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    if (it != null) {
                        adapter = RaMAdapter(it, onItemClick = {character ->
                            navigateToCharacterDetails(character)
                        })
                    }
                }
            }
        }
    }

    private fun navigateToCharacterDetails(character: Character) {
        Log.i("Character: ", character.name)
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("character", character)
        startActivity(intent)
    }
}