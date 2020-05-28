package it.cook.network

import it.cook.model.RecipeData
import retrofit2.http.GET

interface ApiService {

    @GET("db")
    suspend fun getRecipes(): RecipeData
}