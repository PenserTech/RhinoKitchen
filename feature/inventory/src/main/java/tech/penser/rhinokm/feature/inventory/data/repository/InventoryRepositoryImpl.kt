package tech.penser.rhinokm.feature.inventory.data.repository

import tech.penser.rhinokm.feature.inventory.data.local.StorageLocationDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.data.local.MeasurementUnitDao
import tech.penser.rhinokm.feature.inventory.data.local.model.toDomain
import tech.penser.rhinokm.feature.inventory.data.local.model.toEntity
import tech.penser.rhinokm.feature.inventory.domain.model.InventoryItem
import tech.penser.rhinokm.feature.inventory.domain.model.MeasurementUnit
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository

class InventoryRepositoryImpl(
    private val storageLocationDao: StorageLocationDao,
    private val measurementUnitDao: MeasurementUnitDao,
) : InventoryRepository {

    override fun getAllInventoryItems(): Flow<List<InventoryItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getInventoryItemById(id: SafeUuid): InventoryItem? {
        TODO("Not yet implemented")
    }

    override suspend fun addInventoryItem(item: InventoryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updateInventoryItem(item: InventoryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInventoryItem(id: SafeUuid) {
        TODO("Not yet implemented")
    }

    override fun getAllStorageLocations(): Flow<List<StorageLocation>> =
        storageLocationDao.getAll().map {  entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun getStorageLocationById(id: SafeUuid): StorageLocation? =
        storageLocationDao.getById(id)?.toDomain()


    override suspend fun addStorageLocation(location: StorageLocation) =
        storageLocationDao.insert(location.toEntity())


    override suspend fun updateStorageLocation(location: StorageLocation) {
        // when update returns 0, it means the item does not exist, so insert it
        if (storageLocationDao.update(location.toEntity()) == 0) {
            storageLocationDao.insert(location.toEntity())
        }
    }

    override suspend fun deleteStorageLocation(id: SafeUuid) {
        storageLocationDao.delete(id)
    }

    override fun getAllMeasurementUnits(): Flow<List<MeasurementUnit>> =
        measurementUnitDao.getAll().map {  entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun getMeasurementUnitById(id: SafeUuid): MeasurementUnit? =
        measurementUnitDao.getById(id)?.toDomain()

    override suspend fun addMeasurementUnit(unit: MeasurementUnit) =
        measurementUnitDao.insert(unit.toEntity())

    override suspend fun updateMeasurementUnit(unit: MeasurementUnit) {
        // when update returns 0, it means the item does not exist, so insert it
        if (measurementUnitDao.update(unit.toEntity()) == 0) {
            measurementUnitDao.insert(unit.toEntity())
        }

    }



    override suspend fun deleteMeasurementUnit(id: SafeUuid) =
        measurementUnitDao.delete(id)

}