package com.example.wk7taskprecious

import cilent.RetrofitInstance
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.load.HttpException
import java.io.IOException

class ActivityDetails : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val pokemon_name = intent.getStringExtra("name")

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getPokemon(pokemon_name.toString())
            } catch (e: IOException) {
                println("IOException")
                return@launchWhenCreated
            } catch (e: HttpException) {
                println("HttpException")
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                val pokemon = response.body()!!
                findViewById<TextView>(R.id.pokemon_display_name).text = pokemon.name
                findViewById<TextView>(R.id.pokemon_display_height).text = "${pokemon.height} M"
            } else {
                println("Failed")
            }
        }
    }
}

