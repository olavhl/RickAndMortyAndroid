package com.olav.rickandmorty.model

import java.io.Serializable

data class Characters(
    val results: MutableList<Character>
)

class Character: Serializable {
    val id: String = ""
    val name: String = ""
    val status: String = ""
    val species: String = ""
    val image: String = ""
}
