package com.example.recipeapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _repo = CategoryRepo()
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = _repo.getCategories()
                _categoriesState.value =
                    _categoriesState.value.copy(loading = false, list = response.categories)


            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error Fetching ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val error: String? = null,
        val list: List<Category> = emptyList()
    )
}