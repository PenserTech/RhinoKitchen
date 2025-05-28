package tech.penser.rhinokm.feature.recipes.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation.NavHostController

@Composable
fun RecipesScreen(navController: NavHostController) {
    Text(
        text = "Recipes Screen",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary
    )
}