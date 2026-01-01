package com.example.recipeapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeView(viewState:MainViewModel.RecipeState ,navToDetail:(Category) -> Unit) {

    if (viewState.loading) {
        CircularProgressIndicator(strokeWidth = 8.dp)
    } else if (!viewState.loading) {
        MainScreen(viewState.list,navToDetail)
    } else {
       Problem()
    }

}

@Composable
fun MainScreen(categories: List<Category>,navToDetail:(Category) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        items(categories) { category ->
            CategoryScreen(category = category,navToDetail)

        }

    }
}

@Composable
fun CategoryScreen(category: Category,navToDetail:(Category) -> Unit) {
    Column(Modifier.fillMaxWidth().clickable { navToDetail(category) }) {
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
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun Problem() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            "Something Went Wrong",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
        )
    }
}
