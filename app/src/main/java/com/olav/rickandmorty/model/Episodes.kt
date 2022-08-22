package com.olav.rickandmorty.model

data class Episodes(
    val results: List<Episode>,
)

class Episode {
    val id: Int? = null
    val name: String? = null
    val episode: String? = null
    val characters: List<String>? = null
}