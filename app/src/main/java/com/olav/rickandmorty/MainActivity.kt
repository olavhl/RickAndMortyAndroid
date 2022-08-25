package com.olav.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.olav.rickandmorty.databinding.ActivityMainBinding
import com.olav.rickandmorty.fragments.CharacterFragment
import com.olav.rickandmorty.fragments.EpisodeFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var myString: String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(CharacterFragment())
        Log.e("HELLO", myString)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.characters -> replaceFragment(CharacterFragment())
                R.id.episodes -> replaceFragment(EpisodeFragment())

                else -> {}
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrameLayout, fragment)
        fragmentTransaction.commit()
    }
}