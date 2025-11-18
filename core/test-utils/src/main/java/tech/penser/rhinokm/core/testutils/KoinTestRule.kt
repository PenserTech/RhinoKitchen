package tech.penser.rhinokm.core.testutils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

/**
 * A JUnit TestRule that starts and stops Koin for each test.
 *
 * This allows Koin to be initialized with specific modules for instrumentation tests,
 * ensuring a clean Koin context for every test.
 *
 * Usage:
 * ```kotlin
 * class MyInstrumentationTest {
 *     @get:Rule
 *     val koinTestRule = KoinTestRule(
 *         modules = listOf(myFeatureModule, myOtherModule)
 *     )
 *
 *     // ... your tests ...
 * }
 *
 * **/

class KoinTestRule(private val modules: List<Module>): TestRule {
    override fun apply(
        base: Statement,
        description: Description,
    ): Statement {
        return object : Statement() {
            override fun evaluate() {
                val context = ApplicationProvider.getApplicationContext<Context>()
                startKoin {
                    androidContext(context) // Provide Android context
                    modules(modules) // Load the provided modules
                }

                try {
                    base.evaluate()
                } finally {
                    stopKoin()
                }
            }


        }
    }
}