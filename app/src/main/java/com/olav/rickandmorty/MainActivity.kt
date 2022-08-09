package com.olav.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olav.rickandmorty.adapter.RaMAdapter
import com.olav.rickandmorty.viewmodels.RaMViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val ramViewModel = ViewModelProvider(this)[RaMViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ramViewModel.loadCharacters()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        lifecycleScope.launchWhenCreated {
            recyclerView.apply {
                ramViewModel.stateFlow.collectLatest {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    if (it != null) {
                        adapter = RaMAdapter(it)
                    }
                }
            }
        }
    }
}