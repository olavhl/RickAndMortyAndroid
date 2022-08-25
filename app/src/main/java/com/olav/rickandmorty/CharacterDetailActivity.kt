package com.olav.rickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.olav.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.olav.rickandmorty.databinding.ActivityMainBinding
import com.olav.rickandmorty.di.SecondRandomString
import com.olav.rickandmorty.http.characters.fetchCharacter
import com.olav.rickandmorty.model.Character
import com.olav.rickandmorty.retrofit.RamApiClient
import com.olav.rickandmorty.retrofit.character.CharacterApi
import com.olav.rickandmorty.viewmodels.character.CharacterDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity(), Serializable {
    @SecondRandomString
    @Inject lateinit var anotherString: String

    private lateinit var binding: ActivityCharacterDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ramApiClient = RamApiClient.buildService(CharacterApi::class.java)
        val vm = CharacterDetailViewModel(ramApiClient.fetchCharacter())

        val characterId = intent.getSerializableExtra("character-id")

        Log.e("Test-String", anotherString)
        if (characterId != null) {
            vm.loadCharacter(characterId.toString())
        }


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