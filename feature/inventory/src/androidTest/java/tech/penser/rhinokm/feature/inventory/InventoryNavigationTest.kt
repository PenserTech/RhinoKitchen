package tech.penser.rhinokm.feature.inventory

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.feature.inventory.domain.navigation.InventoryNavHost
import tech.penser.rhinokm.core.testutils.BaseComposeNavTest

/**
 * UI tests for the inventory navigation flow.
 *
 * This class tests the navigation between different screens within the inventory feature.
 * It verifies that clicking on navigation elements correctly navigates to the intended screens
 * and that the landing screen displays the expected elements.
 */
@RunWith(AndroidJUnit4::class)
class InventoryNavigationTest: BaseComposeNavTest() {
    
    @Test
    fun inventoryNavigation_landingScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            InventoryNavHost(navController)
        }
        
        // Verify landing screen elements are displayed
        // FAB text requires unmerged tree to be found because
        // the text is embedded in a Text composable
        assertNodeWithTextDisplayed(text = "Start New Inventory", useUnmergedTree = true)
        assertNodeWithTextDisplayed("Items")
        assertNodeWithTextDisplayed("Storage")
        assertNodeWithTextDisplayed("Units")
    }
    
    @Test
    fun inventoryNavigation_clickStorageNavigatesToStorageScreen() {
        composeTestRule.setContent {
            InventoryNavHost(navController)
        }
        
        // Click on Storage button
        clickNodeWithText("Storage")
        
        // Verify we're on the storage screen
        assertNodeWithTextDisplayed("Mock Storage Location List Screen")
    }
    
    @Test
    fun inventoryNavigation_clickItemsNavigatesToItemsScreen() {
        composeTestRule.setContent {
            InventoryNavHost(navController)
        }
        
        // Click on Items button
        clickNodeWithText("Items")
        
        // Verify we're on the items screen
        assertNodeWithTextDisplayed("Mock Item List Screen")
    }
    
    @Test
    fun inventoryNavigation_clickUnitsNavigatesToUnitsScreen() {
        composeTestRule.setContent {
            InventoryNavHost(navController)
        }
        
        // Click on Units button
        clickNodeWithText("Units")
        
        // Verify we're on the units screen
        assertNodeWithTextDisplayed("Mock Units List Screen")
    }
}