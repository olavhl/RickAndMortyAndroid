package com.olav.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.R
import com.olav.rickandmorty.model.Episode
import com.olav.rickandmorty.model.Episodes

class EpisodeListAdapter(
    private val episodes: Episodes,
) : RecyclerView.Adapter<EpisodeListAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_list_item, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        return holder.bindView(episodes.results[position])
    }

    override fun getItemCount(): Int {
        return episodes.results.size
    }

    inner class EpisodeViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvEpisodeTitle: TextView = itemView.findViewById(R.id.tvEpisodeTitle)
        private val tvEpisodeCharacterCount: TextView =
            itemView.findViewById(R.id.tvEpisodeCharacterCount)

        fun bindView(episode: Episode) {
            tvEpisodeTitle.text = "${episode.episode}: ${episode.name}"
            tvEpisodeCharacterCount.text = "Characters: ${episode.characters?.size}"
        }
    }
}
