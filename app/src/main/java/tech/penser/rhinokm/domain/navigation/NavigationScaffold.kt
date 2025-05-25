package tech.penser.rhinokm.domain.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import tech.penser.rhinokm.HomeScreen
import tech.penser.rhinokm.feature.inventory.presentation.InventoryScreen
import tech.penser.rhinokm.feature.orders.presentation.OrdersScreen
import tech.penser.rhinokm.feature.recipes.presentation.RecipesScreen
import tech.penser.rhinokm.feature.settings.presentation.SettingsScreen

@Composable
fun NavigationScaffold() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = stringResource(it.contentDescription)
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        },
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationBarContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationRailContainerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationRailContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Box(modifier = Modifier
            .fillMaxSize() // Takes up the space provided by the scaffold's content area
            .safeDrawingPadding(), // Insets the actual content
            contentAlignment = Alignment.Center
        ) {
            when (currentDestination) {
                AppDestinations.HOME -> HomeScreen()
                AppDestinations.RECIPES -> RecipesScreen()
                AppDestinations.ORDERS -> OrdersScreen()
                AppDestinations.INVENTORY -> InventoryScreen()
                AppDestinations.SETTINGS -> SettingsScreen()
            }
        }
    }
}