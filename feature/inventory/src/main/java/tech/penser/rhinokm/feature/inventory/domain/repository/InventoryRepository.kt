package tech.penser.rhinokm.feature.inventory.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.domain.model.InventoryItem
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation

interface InventoryRepository {
    fun getAllInventoryItems(): Flow<List<InventoryItem>>

    suspend fun getInventoryItemById(id: SafeUuid): InventoryItem?

    suspend fun addInventoryItem(location: InventoryItem)

    suspend fun updateInventoryItem(location: InventoryItem)

    suspend fun deleteInventoryItem(id: SafeUuid)

    fun getAllStorageLocations(): Flow<List<StorageLocation>>

    suspend fun getStorageLocationById(id: SafeUuid): StorageLocation?

    suspend fun addStorageLocation(location: StorageLocation)

    suspend fun updateStorageLocation(location: StorageLocation)

    suspend fun deleteStorageLocation(id: SafeUuid)
}