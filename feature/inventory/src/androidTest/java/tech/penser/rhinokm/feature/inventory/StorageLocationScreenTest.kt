package tech.penser.rhinokm.feature.inventory

import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.core.testutils.BaseComposeNavTest
import tech.penser.rhinokm.core.testutils.KoinTestRule
import tech.penser.rhinokm.feature.inventory.di.inventoryModule
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.navigation.InventoryNavHost
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsScreen
import tech.penser.rhinokm.feature.inventory.presentation.StorageLocationsViewModel

@RunWith(AndroidJUnit4::class)
class StorageLocationScreenTest : BaseComposeNavTest() {

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(
            inventoryModule
        )
    )

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

        val mockRepository = mockk<InventoryRepository>()

        every {mockRepository.getAllStorageLocations() }
            .returns(flowOf(emptyList()))

        val viewModel = StorageLocationsViewModel(mockRepository)

        composeTestRule.setContent {
            StorageLocationsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        assertNodeWithTextDisplayed("Location name") // this  hint text for the first field we want
    }

    @Test
    fun storage_AllLocationsDisplayed_withNameAndAbbreviation() {
        val mockRepository = mockk<InventoryRepository>()

        every { mockRepository.getAllStorageLocations() }
            .returns(flowOf(locations))

        val viewModel = StorageLocationsViewModel(mockRepository)

        composeTestRule.setContent {
            StorageLocationsScreen(navController = navController, viewModel = viewModel)
        }
        // Scroll the list to the bottom to load all items
        composeTestRule.onNodeWithTag("locations_list").performScrollToIndex(locations.size - 1)

        // Now check if Location 10 exists
        composeTestRule.onNodeWithText("Location 7").assertExists()
        composeTestRule.onNodeWithText("L7") .assertExists()
    }

    @Test
    fun displayMode_fieldsAreNotEditable() {
        val locations = listOf( StorageLocation(SafeUuid.random(), "Test Location", "TL") )
        val mockRepository = mockk<InventoryRepository>()

        every { mockRepository.getAllStorageLocations() }
            .returns(flowOf(locations))

        val viewModel = StorageLocationsViewModel(mockRepository)

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

    @Test
    fun editMode_fieldsAreEditable() {
        val locations = listOf( StorageLocation(SafeUuid.random(), "Test Location", "TL") )

        val mockRepository = mockk<InventoryRepository>()

        every { mockRepository.getAllStorageLocations() }
            .returns(flowOf(locations))

        val viewModel = StorageLocationsViewModel(mockRepository)

        composeTestRule.setContent {
            StorageLocationsScreen(navController = navController, viewModel = viewModel)
        }

        assertNodeWithTextDisplayed("Test Location")
        assertNodeWithTextDisplayed("TL")

        // we need a way to set this to edit mode, so we find
        // the card we need by looking for a text field with the value
        // we want to edit and then looking for the edit button.
        composeTestRule.onNode(
            hasParent(hasAnyChild(hasText("Test Location")))
                    and hasContentDescription("Edit location")
        ).performClick()

        //we expect errors because we should not be able to edit the fields
        composeTestRule.onNodeWithText("Test Location").performTextInput("New Text")
        composeTestRule.onNodeWithText("TL").performTextInput("TL")
    }

    /**
     * Here is a list of tests to add:
     *
     * editing a location and clicking save, saves the update
     * editing a location and clicking cancel, does not save the update
     *
     * keyboardDoesNotObscureFields_inPortraitMode
     *
     * keyboardDoesNotObscureFields_inLandscapeMode
     *
     * inputInAddNewRow_survivesOrientationChange
     */

    val locations = listOf(
        StorageLocation(SafeUuid.random(), "Location 1", "L1"),
        StorageLocation(SafeUuid.random(), "Location 2", "L2"),
        StorageLocation(SafeUuid.random(), "Location 4", "L3"),
        StorageLocation(SafeUuid.random(), "Location 3", "L4"),
        StorageLocation(SafeUuid.random(), "Location 5", "L5"),
        StorageLocation(SafeUuid.random(), "Location 6", "L6"),
        StorageLocation(SafeUuid.random(), "Location 7", "L7"),
    )
}
