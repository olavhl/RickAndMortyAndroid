package com.olav.rickandmorty.viewmodels.character

import com.olav.rickandmorty.http.characters.FetchCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterDetailViewModelTest {
    private lateinit var vm: CharacterDetailViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        val api = Mockito.mock(FetchCharacter::class.java)
        vm = CharacterDetailViewModel(api)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun returnsNullFromCharacters() {
        assertTrue(vm.character.value == null)
    }

    @Test
    fun loadsInvalidCharacter() {
        vm.loadCharacter("2")
        assertTrue(vm.character.value == null)
    }
}