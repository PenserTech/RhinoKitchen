package tech.penser.rhinokm.domain.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import tech.penser.rhinokm.R

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, Icons.Default.Home, R.string.home),
    RECIPES(R.string.recipes, Icons.Filled.RestaurantMenu, R.string.recipes),
    ORDERS(R.string.orders, Icons.Default.ShoppingCart, R.string.orders),
    INVENTORY(R.string.inventory, Icons.Default.Edit, R.string.inventory),
    SETTINGS(R.string.settings, Icons.Default.Settings, R.string.settings),
}