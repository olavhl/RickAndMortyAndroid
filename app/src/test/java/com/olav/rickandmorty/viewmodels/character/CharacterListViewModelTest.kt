package com.olav.rickandmorty.viewmodels.character

import com.olav.rickandmorty.http.characters.FetchCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterListViewModelTest {

    private lateinit var vm: CharacterListViewModel
    private val mainThreadSurrogate = newSingleThreadContext("Test Thread")

    @Before
    fun setUp() {
        val api = Mockito.mock(FetchCharacters::class.java)
        vm = CharacterListViewModel(api)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadCharacters() {
        vm.loadCharacters()
        assertTrue(vm.characters.value == null)
    }
}