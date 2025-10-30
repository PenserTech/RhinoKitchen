package tech.penser.rhinokm.feature.inventory.data.local

import StorageLocationDao
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.data.local.model.StorageLocationEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
@SmallTest
class StorageLocationDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: StorageLocationDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.storageLocationDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertStorageLocation_and_getById() = runBlocking {
        val storageLocation = StorageLocationEntity(
            id = SafeUuid.random(),
            name = "Walk-in Cooler",
            abbreviation = "WIC"
        )
        dao.insert(storageLocation)

        val loaded: StorageLocationEntity? = dao.getById(storageLocation.id)

        assertNotNull(loaded)
        assertEquals(storageLocation.id, loaded.id)
        assertEquals("Walk-in Cooler", loaded.name)
        assertEquals("WIC", loaded.abbreviation)
    }

    @Test
    fun getAllStorageLocations_returnsAll() = runBlocking {
        val location1 = StorageLocationEntity(SafeUuid.random(), "Freezer", "F")
        val location2 = StorageLocationEntity(SafeUuid.random(), "Dry Storage", "DS")
        dao.insert(location1)
        dao.insert(location2)

        val allLocations = dao.getAll().first()

        assertEquals(2, allLocations.size)
        assertEquals("Dry Storage", allLocations[0].name) // Alphabetical order
        assertEquals("Freezer", allLocations[1].name)
    }

    @Test
    fun updateStorageLocation_updatesData() = runBlocking {
        val originalLocation = StorageLocationEntity(SafeUuid.random(), "Bar", "B")
        dao.insert(originalLocation)

        val updatedLocation = originalLocation.copy(name = "Main Bar", abbreviation = "MB")
        dao.update(updatedLocation)

        val loaded = dao.getById(originalLocation.id)
        assertNotNull(loaded)
        assertEquals("Main Bar", loaded.name)
        assertEquals("MB", loaded.abbreviation)
    }

    @Test
    fun deleteStorageLocation_removesIt() = runBlocking {
        val location = StorageLocationEntity(SafeUuid.random(), "Cellar", "C")
        dao.insert(location)

        dao.delete(location.id)

        val loaded = dao.getById(location.id)
        assertNull(loaded)
    }
}
