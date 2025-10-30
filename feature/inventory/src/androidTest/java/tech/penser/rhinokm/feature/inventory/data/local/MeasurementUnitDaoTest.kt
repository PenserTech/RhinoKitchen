package tech.penser.rhinokm.feature.inventory.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.data.local.model.MeasurementUnitEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class MeasurementUnitDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: MeasurementUnitDao


    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.measurementUnitDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertMeasurementUnit_and_getById() = runBlocking {
        val unit = MeasurementUnitEntity(
            id = SafeUuid.random(),
            name = "Kilogram",
            abbreviation = "kg"
        )

        dao.insert(unit)

        val measurementUnit: MeasurementUnitEntity? = dao.getById(unit.id)

        assertNotNull(measurementUnit)
        assertEquals(unit.id, measurementUnit.id)
        assertEquals("Kilogram", measurementUnit.name)
        assertEquals("kg", measurementUnit.abbreviation)
    }

    @Test
    fun getAllMeasurementUnits_returnsAll() = runBlocking {
        val unit1 = MeasurementUnitEntity(SafeUuid.random(), "Kilogram", "kg")
        val unit2 = MeasurementUnitEntity(name="Litre", abbreviation = "L")
        val unit3 = MeasurementUnitEntity(name="Ounce", abbreviation = "oz")
        val unit4 = MeasurementUnitEntity(name="Crate", abbreviation = "cr")

        dao.insert(unit1)
        dao.insert(unit2)
        dao.insert(unit3)
        dao.insert(unit4)

        val allUnits = dao.getAll().first()
        assertEquals(4, allUnits.size)
        assertEquals("Crate", allUnits[0].name)
        assertEquals("Kilogram", allUnits[1].name)
        assertEquals("Litre", allUnits[2].name)
        assertEquals("Ounce", allUnits[3].name)

    }

    @Test
    fun updateMeasurementUnit_updatesData() = runBlocking {
        val originalUnit = MeasurementUnitEntity(name = "Kilogram", abbreviation ="kg")

        dao.insert(originalUnit)

        val updatedUnit = originalUnit.copy(name = "Pound", abbreviation = "lb")
        dao.update(updatedUnit)

        val loaded = dao.getById(originalUnit.id)
        assertNotNull(loaded)
        assertEquals("Pound", loaded.name)
        assertEquals("lb", loaded.abbreviation)
    }

    @Test
    fun deleteMeasurementUnit_removesIt() = runBlocking {
        val unit = MeasurementUnitEntity(name = "Kilogram", abbreviation = "kg")
        dao.insert(unit)

        dao.delete(unit.id)

        val loaded = dao.getById(unit.id)
        assertNull(loaded)

    }

}