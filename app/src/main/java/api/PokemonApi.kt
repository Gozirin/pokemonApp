package api

import Models.Pokemon
import Models.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("/api/v2/pokemon/")
    suspend fun getPokemonList(): Response<PokemonList>

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<Pokemon>

}