package com.olav.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.adapter.CharacterListAdapter
import com.olav.rickandmorty.http.characters.fetchCharacter
import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.retrofit.character.CharacterApi
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import com.olav.rickandmorty.viewmodels.episode.EpisodeDetailViewModel
import com.olav.rickandmorty.http.episodes.fetchEpisode
import com.olav.rickandmorty.model.Characters

class EpisodeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        val ramApiClient = RamApiClient.buildService(EpisodeApi::class.java)
        val ramCharacterApiClient = RamApiClient.buildService(CharacterApi::class.java)
        val vm = EpisodeDetailViewModel(ramApiClient.fetchEpisode(), ramCharacterApiClient.fetchCharacter())

        val tvEpisodeDetailName: TextView = findViewById(R.id.tvEpisodeDetailName)
        val tvEpisodeDetailNumber: TextView = findViewById(R.id.tvEpisodeDetailNumber)
        val rvEpisodeCharacters: RecyclerView = findViewById(R.id.rvEpisodeCharacters)

        val episodeId = intent.getSerializableExtra("episode-id")

        if (episodeId != null) {
            vm.getEpisode(episodeId.toString())
        }

        val episodeObserver = Observer<Episode?> {
            tvEpisodeDetailName.text = it.name
            tvEpisodeDetailNumber.text = it.episode
            vm.getCharacters()
        }

        val characterObserver = Observer<Characters?> {
            if (it != null) {
                rvEpisodeCharacters.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = CharacterListAdapter(it, onItemClick = { character ->
                        val intent = Intent(context, CharacterDetailActivity::class.java)
                        intent.putExtra("character-id", character.id)
                        startActivity(intent)
                    })
                }
            }
        }

        vm.episode.observe(this, episodeObserver)
        vm.characters.observe(this, characterObserver)
    }

}