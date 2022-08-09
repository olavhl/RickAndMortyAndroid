package com.olav.rickandmorty.model

data class Characters(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)