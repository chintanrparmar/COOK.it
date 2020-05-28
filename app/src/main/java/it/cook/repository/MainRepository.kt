package it.cook.repository

import it.cook.network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getRecipes() = apiHelper.getRecipes()
}