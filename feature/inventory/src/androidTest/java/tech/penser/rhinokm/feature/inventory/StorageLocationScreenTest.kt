package tech.penser.rhinokm.feature.inventory

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.core.testutils.BaseComposeNavTest
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.navigation.InventoryNavHost
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
            StorageLocationsScreen(navController = navController, viewModel = StorageLocationsViewModel())
        }

        assertNodeWithTextDisplayed("Location name") // this  hint text for the first field we want
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun storage_AllLocationsDisplayed_withNameAndAbbreviation() {
        val viewModel = StorageLocationsViewModel()

        println("ViewModel has ${viewModel.locations.size} locations")
        viewModel.locations.forEachIndexed { index, location ->
            println("$index: ${location.name} - ${location.abbreviation}")
        }

        composeTestRule.setContent {
            StorageLocationsScreen(navController = navController, viewModel = viewModel)
        }
        // Scroll the list to the bottom to load all items
        composeTestRule.onNodeWithTag("locations_list").performScrollToIndex(9)

        // Now check if Location 10 exists
        composeTestRule.onNodeWithText("Location 10").assertExists()
        composeTestRule.onNodeWithText("Location 11").assertExists()

        composeTestRule.onNodeWithText("L11") .assertExists()
    }

    @Test
    fun displayMode_fieldsAreNotEditable() {
        val viewModel = StorageLocationsViewModel()
        composeTestRule.setContent {
            StorageLocationsScreen(navController = navController, viewModel = viewModel)
        }

        //need to verify these items are only editable after clicking on them. Although that seems dangerous,
        //like it might be too easy to accidentally edit something. Maybe think about long press.
        //tried things like assertIsNotEnabled(), but they did not help For some reason the test
    // sees these fields as being editable.
    }

    /**
     * Here is a list of tests to add:
     *
     * displayMode_fieldsAreNotEditable
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