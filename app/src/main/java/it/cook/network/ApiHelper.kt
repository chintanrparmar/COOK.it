package it.cook.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRecipes() = apiService.getRecipes()
}