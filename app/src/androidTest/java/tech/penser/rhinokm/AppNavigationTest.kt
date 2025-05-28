package tech.penser.rhinokm

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.core.testutils.BaseComposeNavTest
import tech.penser.rhinokm.domain.navigation.NavigationScaffold

/**
 * Tests the main application navigation defined in [NavigationScaffold].
 *
 * This class verifies:
 *  - The application starts on the correct initial screen (Home).
 *  - Navigation to each top-level destination works as expected.
 *  - Switching between different top-level destinations is functional.
 *  - Nested navigation state (e.g., within the Inventory feature) is preserved
 *    when navigating away and returning to that feature.
 *
 * It extends [BaseComposeNavTest] to inherit common Compose testing setup and utility functions.
 */
@RunWith(AndroidJUnit4::class)
class AppNavigationTest : BaseComposeNavTest() {
    
    @Test
    fun appNavigation_startsOnHomeScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Debug: Print what's displayed on start
        composeTestRule.onRoot().printToLog("HOME_SCREEN_DEBUG")
        
        // Verify we start on the home screen
        // Home screen shows the color test surface with full color values
        assertNodeWithTextDisplayed("Primary:", substring = true)
        assertNodeWithTextDisplayed("Secondary:", substring = true)
        assertNodeWithTextDisplayed("Tertiary:", substring = true)
    }
    
    @Test
    fun appNavigation_navigateToInventoryShowsInventoryLanding() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Inventory navigation item
        clickNodeWithText("Inventory")

        // Verify we're on the inventory landing screen
        assertNodeWithTextDisplayed(
            "Start New Inventory",
            useUnmergedTree = true
        )
        assertNodeWithTextDisplayed("Items")
        assertNodeWithTextDisplayed("Storage")
        assertNodeWithTextDisplayed("Units")
    }
    
    @Test
    fun appNavigation_navigateToOrdersShowsOrdersScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Orders navigation item
        clickNodeWithText("Orders")
        
        // Verify we're on the orders screen
        assertNodeWithTextDisplayed("Orders Screen")
    }
    
    @Test
    fun appNavigation_navigateToRecipesShowsRecipesScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Recipes navigation item
        clickNodeWithText("Recipes")
        
        // Verify we're on the recipes screen
        assertNodeWithTextDisplayed("Recipes Screen")
    }
    
    @Test
    fun appNavigation_navigateToSettingsShowsSettingsScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Settings navigation item
        clickNodeWithText("Settings")
        
        // Verify we're on the settings screen
        assertNodeWithTextDisplayed("Settings Screen")
    }
    
    @Test
    fun appNavigation_switchBetweenDestinations() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Start on Home, verify home content
        assertNodeWithTextDisplayed("Primary:", substring = true)
        
        // Navigate to Inventory
        clickNodeWithText("Inventory")
        assertNodeWithTextDisplayed("Start New Inventory", useUnmergedTree = true)
        
        // Navigate to Orders
        clickNodeWithText("Orders")
        assertNodeWithTextDisplayed("Orders Screen")
        
        // Navigate back to Home
        clickNodeWithText("Home")
        assertNodeWithTextDisplayed("Primary:", substring = true)
    }
    
    @Test
    fun appNavigation_inventoryPreservesNavigationState() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Navigate to Inventory (starts on landing screen)
        clickNodeWithText("Inventory")
        assertNodeWithTextDisplayed("Start New Inventory", useUnmergedTree = true)
        
        // Navigate within inventory to Storage screen
        clickNodeWithText("Storage")
        
        // Debug: Print what's displayed after clicking Storage
        composeTestRule.onRoot().printToLog("AFTER_STORAGE_CLICK")

        assertNodeWithTextDisplayed("Mock Storage Location List Screen")
        
        // Leave inventory - go to Orders
        clickNodeWithText("Orders")
        assertNodeWithTextDisplayed("Orders Screen")
        
        // Return to inventory - should be back on Storage screen, not landing
        clickNodeWithText("Inventory")
        
        // Debug: Print what's displayed when returning to inventory
        composeTestRule.onRoot().printToLog("RETURN_TO_INVENTORY")

        assertNodeWithTextDisplayed("Mock Storage Location List Screen")
        
        // Verify we're NOT on the landing screen
        composeTestRule.onNodeWithText("Start New Inventory", useUnmergedTree = true).assertDoesNotExist()
    }
}