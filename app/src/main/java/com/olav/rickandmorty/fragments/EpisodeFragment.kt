package com.olav.rickandmorty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.R
import com.olav.rickandmorty.adapter.EpisodeListAdapter
import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.model.Episodes
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.retrofit.episode.EpisodeApi
import com.olav.rickandmorty.viewmodels.episode.EpisodeListViewModel
import com.olav.rickandmorty.viewmodels.episode.fetchEpisodes
import kotlinx.coroutines.launch

class EpisodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ramApiClient = RamApiClient.buildService(EpisodeApi::class.java)
        val vm = EpisodeListViewModel(ramApiClient.fetchEpisodes())

        vm.loadEpisodes()

        val rvEpisodes: RecyclerView = view.findViewById(R.id.rvEpisodes)

        val episodesObserver = Observer<Episodes?> {
            if (it != null) {
                rvEpisodes.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = EpisodeListAdapter(it)
                }
            }
        }

        vm.episodes.observe(viewLifecycleOwner, episodesObserver)
    }
}