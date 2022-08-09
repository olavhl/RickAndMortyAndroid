package com.olav.rickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.olav.rickandmorty.model.Character
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.io.Serializable

class CharacterDetailActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        val character = intent.getSerializableExtra("charact") as Character
        Log.i("MYCharacter", character.toString())


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val ivImage: ImageView = findViewById<ImageView>(R.id.ivImage)
        val tvName: TextView = findViewById<TextView>(R.id.tvName)
        val tvSpecies: TextView = findViewById<TextView>(R.id.tvSpecies)
        val tvStatus: TextView = findViewById<TextView>(R.id.tvStatus)

        Picasso.get().load(character.image).into(ivImage)
        tvName.text = character.name
        tvSpecies.text = character.species
        tvStatus.text = character.status
    }
}