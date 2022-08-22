package com.olav.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.adapter.CharacterListAdapter
import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.retrofit.CharacterApi
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.viewmodels.CharacterListViewModel
import com.olav.rickandmorty.http.fetchCharacters
import kotlinx.coroutines.flow.collectLatest
import java.io.Serializable

class MainActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        // API
        val raMApiClient = RamApiClient.buildService(CharacterApi::class.java)

        val ramViewModel = CharacterListViewModel(raMApiClient.fetchCharacters())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ramViewModel.loadCharacters()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        lifecycleScope.launchWhenCreated {
            recyclerView.apply {
                ramViewModel.stateFlow.collectLatest {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    if (it != null) {
                        adapter = CharacterListAdapter(it, onItemClick = { character ->
                            navigateToCharacterDetails(character)
                        })
                    }
                }
            }
        }
    }

    private fun navigateToCharacterDetails(character: Character) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("character", character)
        startActivity(intent)
    }
}