package tech.penser.rhinokm.feature.inventory

import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTestRule
import tech.penser.rhinokm.core.testutils.BaseComposeNavTest
import tech.penser.rhinokm.feature.inventory.di.inventoryModule
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.navigation.InventoryNavHost
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@RunWith(AndroidJUnit4::class)
class StorageLocationScreenTest : BaseComposeNavTest() {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // Print Koin logs for debugging
        // printLogger(Level.DEBUG)
        modules(
            inventoryModule, // The module that provides StorageLocationsViewModel, etc.
        )
    }

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
            StorageLocationsScreen(navController = navController, viewModel = StorageLocationsViewModel())
        }

        assertNodeWithTextDisplayed("Location name") // this  hint text for the first field we want
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun storage_AllLocationsDisplayed_withNameAndAbbreviation() {
        val viewModel = StorageLocationsViewModel()

        composeTestRule.setContent {
            StorageLocationsScreen(navController = navController, viewModel = viewModel)
        }
        // Scroll the list to the bottom to load all items
        composeTestRule.onNodeWithTag("locations_list").performScrollToIndex(9)

        // Now check if Location 10 exists
        composeTestRule.onNodeWithText("Location 10").assertExists()
        composeTestRule.onNodeWithText("L10") .assertExists()
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun displayMode_fieldsAreNotEditable() {
        val locations = listOf( StorageLocation(Uuid.random(), "Test Location", "TL") )
        val viewModel = StorageLocationsViewModel()
        viewModel.locations.clear()
        viewModel.locations.addAll(locations)
        composeTestRule.setContent {
            StorageLocationsScreen(navController = navController, viewModel = viewModel)
        }

        assertNodeWithTextDisplayed("Test Location")
        assertNodeWithTextDisplayed("TL")

        //we expect errors because we should not be able to edit the fields
        assert(
            composeTestRule
                .onNodeWithText("Test Location")
                .throwsErrorOn { performTextInput("New Text") }
        )
        assert(
            composeTestRule
                .onNodeWithText("TL")
                .throwsErrorOn { performTextInput("New") }
        )
    }

    /**
     * Here is a list of tests to add:
     *
     *
     * keyboardDoesNotObscureFields_inPortraitMode
     *
     * keyboardDoesNotObscureFields_inLandscapeMode
     *
     * inputInAddNewRow_survivesOrientationChange
     */

    @OptIn(ExperimentalUuidApi::class)
    val locations = listOf(
        StorageLocation(Uuid.random(), "Location 1", "L1"),
        StorageLocation(Uuid.random(), "Location 2", "L2"),
        StorageLocation(Uuid.random(), "Location 4", "L3"),
        StorageLocation(Uuid.random(), "Location 3", "L4"),
        StorageLocation(Uuid.random(), "Location 5", "L5"),
        StorageLocation(Uuid.random(), "Location 6", "L6"),
        StorageLocation(Uuid.random(), "Location 7", "L7"),
    )
}
