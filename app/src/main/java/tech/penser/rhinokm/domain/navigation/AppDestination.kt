package tech.penser.rhinokm.domain.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import tech.penser.rhinokm.R

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    @StringRes val contentDescription: Int,
) {
    HOME(R.string.home, Icons.Outlined.Home, Icons.Filled.Home, R.string.home),
    RECIPES(R.string.recipes, Icons.Outlined.RestaurantMenu, Icons.Filled.RestaurantMenu, R.string.recipes),
    ORDERS(R.string.orders, Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart, R.string.orders),
    INVENTORY(R.string.inventory, Icons.Outlined.Edit, Icons.Filled.Edit, R.string.inventory),
    SETTINGS(R.string.settings, Icons.Outlined.Settings, Icons.Filled.Settings, R.string.settings),
}