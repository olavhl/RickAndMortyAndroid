package com.olav.rickandmorty

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.olav.rickandmorty.http.characters.fetchCharacter
import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.retrofit.character.CharacterApi
import com.olav.rickandmorty.viewmodels.character.CharacterDetailViewModel
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
        val progressBar: ProgressBar = findViewById(R.id.progressBarCharacterDetail)


        val characterObserver = Observer<Character?> {
            Picasso.get().load(it.image).into(ivImage)
            tvName.text = it.name
            tvSpecies.text = it.species
            tvStatus.text = it.status

            progressBar.isVisible = false
        }

        vm.character.observe(this, characterObserver)
    }
}