package tech.penser.rhinokm.feature.inventory

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.core.testutils.BaseComposeNavTest
import tech.penser.rhinokm.feature.inventory.domain.navigation.InventoryNavHost
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationListScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationViewModel

@RunWith(AndroidJUnit4::class)
class StorageLocationScreenTest : BaseComposeNavTest() {

    @Test
    fun inventory_NavigationToStorageLocationLoadsStorageLocationScreen() {
        //starting on Inventory landing page
        composeTestRule.setContent {
            InventoryNavHost(navController)
        }

        clickNodeWithText("Storage")
        assertNodeWithTextDisplayed("Storage Locations") // this is the app bar title
    }

    @Test
    fun store_CanAddNewStorageLocationWithValidInformation() {
        composeTestRule.setContent {
            StorageLocationListScreen(navController = navController, viewModel = StorageLocationViewModel())
        }

        assertNodeWithTextDisplayed("Location name") // this  hint text for the first field we want
    }
}