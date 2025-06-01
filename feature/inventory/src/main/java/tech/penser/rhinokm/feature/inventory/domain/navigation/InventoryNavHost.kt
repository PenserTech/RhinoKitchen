package tech.penser.rhinokm.feature.inventory.domain.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import tech.penser.rhinokm.feature.inventory.R
import tech.penser.rhinokm.feature.inventory.presentation.InventoryLandingScreen
import tech.penser.rhinokm.feature.inventory.presentation.ItemListScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel
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
            val viewModel = koinViewModel<StorageLocationsViewModel>()
            StorageLocationsScreen(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier.padding(dimensionResource(R.dimen.spacing_normal))
            )
        }
        composable(InventoryDestination.Units.route) {
            UnitsListScreen()
        }
    }
}

