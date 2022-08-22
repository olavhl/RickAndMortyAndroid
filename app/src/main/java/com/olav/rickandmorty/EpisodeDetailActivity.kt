package com.olav.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import com.olav.rickandmorty.viewmodels.episode.EpisodeDetailViewModel
import com.olav.rickandmorty.viewmodels.episode.fetchEpisode

class EpisodeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        val ramApiClient = RamApiClient.buildService(EpisodeApi::class.java)
        val vm = EpisodeDetailViewModel(ramApiClient.fetchEpisode())

        val tvEpisodeDetailName: TextView = findViewById(R.id.tvEpisodeDetailName)
        val tvEpisodeDetailNumber: TextView = findViewById(R.id.tvEpisodeDetailNumber)

        val episodeId = intent.getSerializableExtra("episode-id")

        if (episodeId != null) {
            vm.getEpisode(episodeId.toString())
        }

        val episodeObserver = Observer<Episode?> {
            tvEpisodeDetailName.text = it.name
            tvEpisodeDetailNumber.text = it.episode
        }

        vm.episode.observe(this, episodeObserver)
    }

}