package tech.penser.rhinokm.feature.inventory

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.printToLog
//import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.feature.inventory.domain.navigation.InventoryNavHost

@RunWith(AndroidJUnit4::class)
class InventoryNavigationTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun inventoryNavigation_landingScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            InventoryNavHost()
        }
        
        // Verify landing screen elements are displayed
        // FAB text requires unmerged tree to be found because the text is embedded in a Text composable
        composeTestRule.onNodeWithText("Start New Inventory", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Items").assertIsDisplayed()
        composeTestRule.onNodeWithText("Storage").assertIsDisplayed()
        composeTestRule.onNodeWithText("Units").assertIsDisplayed()
    }
    
    @Test
    fun inventoryNavigation_clickStorageNavigatesToStorageScreen() {
        composeTestRule.setContent {
            InventoryNavHost()
        }
        
        // Click on Storage button
        composeTestRule.onNodeWithText("Storage").performClick()
        
        // Verify we're on the storage screen
        composeTestRule.onNodeWithText("Mock Storage Location List Screen").assertIsDisplayed()
    }
    
    @Test
    fun inventoryNavigation_clickItemsNavigatesToItemsScreen() {
        composeTestRule.setContent {
            InventoryNavHost()
        }
        
        // Click on Items button
        composeTestRule.onNodeWithText("Items").performClick()
        
        // Verify we're on the items screen
        composeTestRule.onNodeWithText("Mock Item List Screen").assertIsDisplayed()
    }
    
    @Test
    fun inventoryNavigation_clickUnitsNavigatesToUnitsScreen() {
        composeTestRule.setContent {
            InventoryNavHost()
        }
        
        // Click on Units button
        composeTestRule.onNodeWithText("Units").performClick()
        
        // Verify we're on the units screen
        composeTestRule.onNodeWithText("Mock Units List Screen").assertIsDisplayed()
    }
}