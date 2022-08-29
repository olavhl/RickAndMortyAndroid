package com.olav.rickandmorty.viewmodels.episode

import com.olav.rickandmorty.http.characters.FetchCharacter
import com.olav.rickandmorty.http.episodes.FetchEpisode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class EpisodeDetailViewModelTest {
    private lateinit var vm: EpisodeDetailViewModel
    private val mainThreadSurrogate = newSingleThreadContext("EpisodeDetailTestThread")

    @Before
    fun setup() {
        val episodeApi = Mockito.mock(FetchEpisode::class.java)
        val characterApi = Mockito.mock(FetchCharacter::class.java)
        vm = EpisodeDetailViewModel(episodeApi, characterApi)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCharacters() {
        vm.getCharacters()
        assertTrue(vm.characters.value == null)
    }

    @Test
    fun testGetEpisode() {
        vm.getEpisode("test")
        assertTrue(vm.episode.value == null)
    }


}