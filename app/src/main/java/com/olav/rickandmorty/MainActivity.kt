package com.olav.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.adapter.CharacterListAdapter
import com.olav.rickandmorty.retrofit.CharacterApi
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.viewmodels.CharacterListViewModel
import com.olav.rickandmorty.http.characters.fetchCharacters
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
                            val intent = Intent(context, CharacterDetailActivity::class.java)
                            intent.putExtra("character-id", character.id)
                            startActivity(intent)
                        })
                    }
                }
            }
        }
    }
}