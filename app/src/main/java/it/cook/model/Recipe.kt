package it.cook.model

data class Recipe(
    val cookingTime: String,
    val cover: String,
    val prepTime: String,
    val category: String?,
    val ingredients: String?,
    val stepsGuide: String?,
    val id: Int,
    val title: String
)