package tech.penser.rhinokm

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.domain.navigation.NavigationScaffold

@RunWith(AndroidJUnit4::class)
class AppNavigationTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun appNavigation_startsOnHomeScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Debug: Print what's actually displayed on start
        composeTestRule.onRoot().printToLog("HOME_SCREEN_DEBUG")
        
        // Verify we start on the home screen
        // Home screen shows the color test surface with full color values
        composeTestRule.onNodeWithText("Primary:", substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Secondary:", substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Tertiary:", substring = true).assertIsDisplayed()
    }
    
    @Test
    fun appNavigation_navigateToInventoryShowsInventoryLanding() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Inventory navigation item
        composeTestRule.onNodeWithText("Inventory").performClick()
        
        // Verify we're on the inventory landing screen
        composeTestRule.onNodeWithText("Start New Inventory", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Items").assertIsDisplayed()
        composeTestRule.onNodeWithText("Storage").assertIsDisplayed()
        composeTestRule.onNodeWithText("Units").assertIsDisplayed()
    }
    
    @Test
    fun appNavigation_navigateToOrdersShowsOrdersScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Orders navigation item
        composeTestRule.onNodeWithText("Orders").performClick()
        
        // Verify we're on the orders screen
        composeTestRule.onNodeWithText("Orders Screen").assertIsDisplayed()
    }
    
    @Test
    fun appNavigation_navigateToRecipesShowsRecipesScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Recipes navigation item
        composeTestRule.onNodeWithText("Recipes").performClick()
        
        // Verify we're on the recipes screen
        composeTestRule.onNodeWithText("Recipes Screen").assertIsDisplayed()
    }
    
    @Test
    fun appNavigation_navigateToSettingsShowsSettingsScreen() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Click on Settings navigation item
        composeTestRule.onNodeWithText("Settings").performClick()
        
        // Verify we're on the settings screen
        composeTestRule.onNodeWithText("Settings Screen").assertIsDisplayed()
    }
    
    @Test
    fun appNavigation_switchBetweenDestinations() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Start on Home, verify home content
        composeTestRule.onNodeWithText("Primary:", substring = true).assertIsDisplayed()
        
        // Navigate to Inventory
        composeTestRule.onNodeWithText("Inventory").performClick()
        composeTestRule.onNodeWithText("Start New Inventory", useUnmergedTree = true).assertIsDisplayed()
        
        // Navigate to Orders
        composeTestRule.onNodeWithText("Orders").performClick()
        composeTestRule.onNodeWithText("Orders Screen").assertIsDisplayed()
        
        // Navigate back to Home
        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.onNodeWithText("Primary:", substring = true).assertIsDisplayed()
    }
    
    @Test
    fun appNavigation_inventoryPreservesNavigationState() {
        composeTestRule.setContent {
            NavigationScaffold()
        }
        
        // Navigate to Inventory (starts on landing screen)
        composeTestRule.onNodeWithText("Inventory").performClick()
        composeTestRule.onNodeWithText("Start New Inventory", useUnmergedTree = true).assertIsDisplayed()
        
        // Navigate within inventory to Storage screen
        composeTestRule.onNodeWithText("Storage").performClick()
        
        // Debug: Print what's displayed after clicking Storage
        composeTestRule.onRoot().printToLog("AFTER_STORAGE_CLICK")
        
        composeTestRule.onNodeWithText("Mock Storage Location List Screen").assertIsDisplayed()
        
        // Leave inventory - go to Orders
        composeTestRule.onNodeWithText("Orders").performClick()
        composeTestRule.onNodeWithText("Orders Screen").assertIsDisplayed()
        
        // Return to inventory - should be back on Storage screen, not landing
        composeTestRule.onNodeWithText("Inventory").performClick()
        
        // Debug: Print what's displayed when returning to inventory
        composeTestRule.onRoot().printToLog("RETURN_TO_INVENTORY")
        
        composeTestRule.onNodeWithText("Mock Storage Location List Screen").assertIsDisplayed()
        
        // Verify we're NOT on the landing screen
        composeTestRule.onNodeWithText("Start New Inventory", useUnmergedTree = true).assertDoesNotExist()
    }
}