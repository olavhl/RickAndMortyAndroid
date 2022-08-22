package com.olav.rickandmorty

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.retrofit.CharacterApi
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.viewmodels.CharacterDetailViewModel
import com.olav.rickandmorty.http.characters.fetchCharacter
import com.squareup.picasso.Picasso
import java.io.Serializable

class CharacterDetailActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        val ramApiClient = RamApiClient.buildService(CharacterApi::class.java)
        val vm = CharacterDetailViewModel(ramApiClient.fetchCharacter())

        val characterId = intent.getSerializableExtra("character-id")

        if (characterId != null) {
            vm.loadCharacter(characterId.toString())
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val ivImage: ImageView = findViewById(R.id.ivImage)
        val tvName: TextView = findViewById(R.id.tvName)
        val tvSpecies: TextView = findViewById(R.id.tvSpecies)
        val tvStatus: TextView = findViewById(R.id.tvStatus)


        val characterObserver = Observer<Character?> {
            Picasso.get().load(it.image).into(ivImage)
            tvName.text = it.name
            tvSpecies.text = it.species
            tvStatus.text = it.status
        }

        vm.character.observe(this, characterObserver)
    }
}