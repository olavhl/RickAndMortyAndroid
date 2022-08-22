package com.olav.rickandmorty.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.CharacterDetailActivity
import com.olav.rickandmorty.R
import com.olav.rickandmorty.adapter.CharacterListAdapter
import com.olav.rickandmorty.http.characters.fetchCharacters
import com.olav.rickandmorty.retrofit.CharacterApi
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.viewmodels.CharacterListViewModel
import kotlinx.coroutines.flow.collectLatest

class CharacterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // API
        val raMApiClient = RamApiClient.buildService(CharacterApi::class.java)

        val ramViewModel = CharacterListViewModel(raMApiClient.fetchCharacters())
        ramViewModel.loadCharacters()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCharacters)


        lifecycleScope.launchWhenCreated {
            recyclerView.apply {
                ramViewModel.stateFlow.collectLatest {
                    layoutManager = LinearLayoutManager(context)
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