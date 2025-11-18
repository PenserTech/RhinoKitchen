package tech.penser.rhinokm.feature.inventory.data.repository

import StorageLocationDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.data.local.MeasurementUnitDao
import tech.penser.rhinokm.feature.inventory.data.local.model.StorageLocationEntity
import tech.penser.rhinokm.feature.inventory.data.local.model.toDomain
import tech.penser.rhinokm.feature.inventory.data.local.model.toEntity
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class InventoryRepositoryTest {

    private lateinit var storageLocationDao: StorageLocationDao
    private lateinit var measurementUnitDao: MeasurementUnitDao
    private lateinit var inventoryRepository: InventoryRepository

    @Before
    fun setup() {
        storageLocationDao = mockk<StorageLocationDao>()
        measurementUnitDao = mockk<MeasurementUnitDao>()
        inventoryRepository = InventoryRepositoryImpl(storageLocationDao, measurementUnitDao)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllStorageLocations returns mapped domain objects from DAO`() = runTest {
        // Given
        val loc1 = StorageLocationEntity(name = "Walk-In Fridge", abbreviation = "WF")
        val loc2 = StorageLocationEntity(name = "Freezer", abbreviation = "FRZ")
        val loc3 = StorageLocationEntity(name = "Refrigerator", abbreviation = "REF")

        val entities = listOf(loc1, loc2, loc3)

        coEvery { storageLocationDao.getAll() } returns flowOf(entities)

        // When
        val results = inventoryRepository.getAllStorageLocations().first()

        // Then
        val expected = entities.map { it.toDomain() }
        assertEquals(expected, results)
    }

    @Test
    fun `getStorageLocationById returns null if not found`() = runTest {
        // Given
        val id = SafeUuid.random()
        coEvery { storageLocationDao.getById(id) } returns null

        //When
        val result = inventoryRepository.getStorageLocationById(id)

        //Then
        assertEquals(null, result)

    }

    @Test
    fun `getStorageLocationById returns mapped domain object from DAO` () = runTest {
        // Given
        val id = SafeUuid.random()
        val entity = StorageLocationEntity(name = "Walk-In Fridge", abbreviation = "WF")
        coEvery { storageLocationDao.getById(id) } returns entity

        // When
        val result = inventoryRepository.getStorageLocationById(id)

        // Then
        assertEquals(entity.toDomain(), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `addStorageLocation adds location to repository`() = runTest {
        // Given
        val location = StorageLocation(name = "Walk-In Fridge", abbreviation = "WF")
        val expectedEntity = location.toEntity()
        coEvery { storageLocationDao.insert(expectedEntity) } returns Unit

        // When
        inventoryRepository.addStorageLocation(location)
        advanceUntilIdle()

        // Then
        // Verify insert called exactly once with expected Entity
        coVerify(exactly = 1) { storageLocationDao.insert(expectedEntity) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateStorageLocation updates existing location in repository`() = runTest {
        // Given
        val existing = StorageLocation(name = "Walk-In Fridge", abbreviation = "WF")
        val entity = existing.toEntity()
        coEvery { storageLocationDao.update(entity) } returns 1

        // When
        inventoryRepository.updateStorageLocation(existing)
        advanceUntilIdle()

        // Then
        // Verify update called exactly once with expected Entity
        coVerify(exactly = 1) { storageLocationDao.update(entity) }
        // Verify insert is not called
        coVerify(exactly = 0) { storageLocationDao.insert(any()) }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateStorageLocation inserts non-existent location in repository`() = runTest {
        // Given
        val existing = StorageLocation(name = "Walk-In Fridge", abbreviation = "WF")
        val entity = existing.toEntity()
        coEvery { storageLocationDao.update(entity) } returns 0
        coEvery { storageLocationDao.insert(entity) } returns Unit

        // When
        inventoryRepository.updateStorageLocation(existing)
        advanceUntilIdle()

        // Then
        // Verify update called exactly once with expected Entity
        coVerify(exactly = 1) { storageLocationDao.update(entity) }
        // Verify insert is not called
        coVerify(exactly = 1) { storageLocationDao.insert(any()) }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `deleteStorageLocation removes the item from data storage`() = runTest {
        // Given
        val toDelete = StorageLocation(name = "Walk-In Fridge", abbreviation = "WF")
        val entity = toDelete.toEntity()
        coEvery { storageLocationDao.delete(any()) } returns Unit

        // When
        inventoryRepository.deleteStorageLocation(toDelete.id)
        advanceUntilIdle()

        // Then
        // Verify delete called exactly once with expected Entity
        coVerify(exactly = 1) { storageLocationDao.delete(toDelete.id) }
    }
}