package tech.penser.rhinokm.feature.inventory.presentation

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class StorageLocationsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: StorageLocationsViewModel
    private val mockRepository  = mockk<InventoryRepository>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialLoad_loadsLocationsFromRepository() = runTest {
        // Given
        val expectedLocations = listOf(
            StorageLocation(SafeUuid.random(), "Test Location 1", "TL1"),
            StorageLocation(SafeUuid.random(), "Test Location 2", "TL2")
        )

        every { mockRepository.getAllStorageLocations() }
            .returns(flowOf(expectedLocations))

        viewModel = StorageLocationsViewModel(mockRepository)

        // We must collect the flow to trigger the upstream collection
        // due to the SharingStarted.WhileSubscribed policy.
        val job = launch { viewModel.locations.collect() }

        testDispatcher.scheduler.advanceUntilIdle() // Ensure the collection and emission happen.

        // Then
        assertEquals(expectedLocations, viewModel.locations.value)

        job.cancel() // Clean up the collector.
    }
}