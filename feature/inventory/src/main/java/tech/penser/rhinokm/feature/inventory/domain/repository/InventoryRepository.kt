package tech.penser.rhinokm.feature.inventory.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.domain.model.InventoryItem
import tech.penser.rhinokm.feature.inventory.domain.model.MeasurementUnit
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation

interface InventoryRepository {
    fun getAllInventoryItems(): Flow<List<InventoryItem>>

    suspend fun getInventoryItemById(id: SafeUuid): InventoryItem?

    suspend fun addInventoryItem(item: InventoryItem)

    suspend fun updateInventoryItem(item: InventoryItem)

    suspend fun deleteInventoryItem(id: SafeUuid)

    fun getAllStorageLocations(): Flow<List<StorageLocation>>

    suspend fun getStorageLocationById(id: SafeUuid): StorageLocation?

    suspend fun addStorageLocation(location: StorageLocation)

    suspend fun updateStorageLocation(location: StorageLocation)

    suspend fun deleteStorageLocation(id: SafeUuid)

    fun getAllMeasurementUnits(): Flow<List<MeasurementUnit>>

    suspend fun getMeasurementUnitById(id: SafeUuid): MeasurementUnit?

    suspend fun addMeasurementUnit(unit: MeasurementUnit)

    suspend fun updateMeasurementUnit(unit: MeasurementUnit)

    suspend fun deleteMeasurementUnit(id: SafeUuid)
}