package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeView() {
    val recipeModel: MainViewModel = viewModel()
    val viewState = recipeModel.categoriesState

    if (viewState.value.loading) {
        //Loading Screen
    } else if (viewState.value.loading == false) {
        MainScreen(viewState.value.list)
    } else {
        Text("Unknown Error")
    }

}

@Composable
fun MainScreen(categories: List<Category>) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(5.dp)) {
        items(categories) { category ->
            CategoryScreen(category = category)

        }

    }
}

@Composable
fun CategoryScreen(category: Category) {
        Column(Modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = "Picture Of Food",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
            Text(
                text = category.strCategory,
                fontSize = (24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
