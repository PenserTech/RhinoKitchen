package tech.penser.rhinokm.feature.inventory.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.penser.rhinokm.feature.inventory.presentation.InventoryLandingScreen
import tech.penser.rhinokm.feature.inventory.presentation.ItemListScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationListScreen
import tech.penser.rhinokm.feature.inventory.presentation.UnitsListScreen

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = InventoryDestination.Landing.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(InventoryDestination.Landing.route) {
            InventoryLandingScreen(
                onStartNewInventory = { /* todo */ },
                onOpenInProgress = { /* todo */ },
                onViewAllInventories = { /* todo */ },
                onManageStorageLocations = {
                    navController.navigate(InventoryDestination.Storage.route)
                },
                onManageUnits = {
                    navController.navigate(InventoryDestination.Units.route)
                },
                onManageInventoryItems = {
                    navController.navigate(InventoryDestination.Items.route)
                }
            )
        }
        composable(InventoryDestination.Items.route) {
            ItemListScreen()
        }
        composable(InventoryDestination.Storage.route) {
            StorageLocationListScreen()
        }
        composable(InventoryDestination.Units.route) {
            UnitsListScreen()
        }
    }
}
