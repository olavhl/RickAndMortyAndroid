package com.olav.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.R
import com.olav.rickandmorty.model.Characters
import com.olav.rickandmorty.model.Character
import com.squareup.picasso.Picasso

class CharacterListAdapter(
    private val characters: Characters,
    var onItemClick: ((Character) -> Unit)? = null,
) : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ram_cards, parent, false)

        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        return holder.bindView(characters.results[position])
    }

    override fun getItemCount(): Int {
        return characters.results.count()
    }

    inner class CharacterListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bindView(character: Character) {
            tvTitle.text = character.name
            Picasso.get().load(character.image).into(image)
            itemView.setOnClickListener {
                onItemClick?.invoke(character)
            }
        }
    }

}
