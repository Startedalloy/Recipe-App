package com.example.recipeapp

import androidx.compose.runtime.Composable

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


sealed class Screen(val route: String) {
    data object CategoryScreen : Screen("categoryScreen")
    data object DetailScreen : Screen("detailScreen")
}

@Composable
fun RecipeNav(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val recipeNavState = recipeViewModel.categoriesState.value

    NavHost(navController = navController, startDestination = Screen.CategoryScreen.route) {
        composable(Screen.CategoryScreen.route) {
            RecipeView(
                viewState = recipeNavState,
                navToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "cat",
                        it
                    )
                    navController.navigate(Screen.DetailScreen.route)
                })
        }
        composable(Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                    ?: Category("", "", "", "")
            CategoryDetail(category)
        }
    }
}