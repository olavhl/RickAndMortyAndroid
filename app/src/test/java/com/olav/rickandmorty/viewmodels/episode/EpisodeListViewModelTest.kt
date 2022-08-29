package com.olav.rickandmorty.viewmodels.episode

import com.olav.rickandmorty.http.episodes.FetchEpisodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class EpisodeListViewModelTest {
    private lateinit var vm: EpisodeListViewModel
    private val mainThreadSurrogate = newSingleThreadContext("EpisodeListTestThread")

    @Before
    fun setup() {
        val api = Mockito.mock(FetchEpisodes::class.java)
        vm = EpisodeListViewModel(api)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadEpisodes() {
        vm.loadEpisodes()
        assertTrue(vm.episodes.value == null)
    }
}