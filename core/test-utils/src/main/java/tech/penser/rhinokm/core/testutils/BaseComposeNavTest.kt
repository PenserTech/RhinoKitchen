package tech.penser.rhinokm.core.testutils

import android.content.Context
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import kotlin.reflect.KClass

/**
 * Base class for Compose Navigation tests.
 *
 * This class provides common setup and utility methods for testing Jetpack Compose navigation.
 * It initializes a [TestNavHostController] and provides helper functions for interacting with
 * UI elements and making assertions.
 *
 * Subclasses should inherit from this class to leverage the shared testing infrastructure.
 */
abstract class BaseComposeNavTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    protected lateinit var navController: TestNavHostController

    @Before
    fun baseSetup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        navController = TestNavHostController(context).apply {
            this.navigatorProvider.addNavigator(ComposeNavigator())
        }
    }

    fun clickNodeWithText(text: String) = composeTestRule.onNodeWithText(text).performClick()

    fun assertNodeWithTextDisplayed(
        text: String,
        useUnmergedTree: Boolean = false,
        substring: Boolean = false,
    ) = composeTestRule
            .onNodeWithText(
                text,
                useUnmergedTree = useUnmergedTree,
                substring = substring
            )
            .assertIsDisplayed()

    fun SemanticsNodeInteraction.throwsErrorOn(
        expectedExceptionType: KClass<out Throwable> = AssertionError::class, // Allow specifying exception type
        actionToPerform: SemanticsNodeInteraction.() -> Unit,
    ): Boolean =
        try {
            this.actionToPerform()
            false
        } catch( err: AssertionError) {
            true
        } catch( err: Throwable) {
            false
        }
}
