package com.example.wk7taskprecious

import adapter.PokemonAdapter
import cilent.RetrofitInstance
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvPokemons = findViewById<RecyclerView>(R.id.rvPokemons)

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getPokemonList()
            } catch (e: IOException) {
                println("IOException")
                return@launchWhenCreated
            } catch (e: HttpException) {
                println("HttpExcpetion")
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                val results = result.results
                val adapter = PokemonAdapter(results)
                rvPokemons.adapter = adapter
                rvPokemons.layoutManager = GridLayoutManager(this@MainActivity, 2)
            } else {
                println("Failed")
            }
        }
    }
}