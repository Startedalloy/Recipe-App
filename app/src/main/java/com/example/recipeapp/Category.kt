package com.example.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class CategoryList(val categories: List<Category>)

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoryList
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }
}
class CategoryRepo{
    suspend fun getCategories(): CategoryList{
        return RetrofitInstance.api.getCategories()
    }
}