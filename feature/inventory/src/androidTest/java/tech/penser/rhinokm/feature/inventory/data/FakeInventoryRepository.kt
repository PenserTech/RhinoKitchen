package tech.penser.rhinokm.feature.inventory.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.domain.model.InventoryItem
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository

class FakeInventoryRepository : InventoryRepository {
    private val _storageLocations =
        MutableStateFlow<LinkedHashMap<SafeUuid, StorageLocation>>(linkedMapOf())
    private val storageLocations: Flow<List<StorageLocation>> =
        _storageLocations.asStateFlow().map { it.values.toList() }

    override fun getAllInventoryItems(): Flow<List<InventoryItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getInventoryItemById(id: SafeUuid): InventoryItem? {
        TODO("Not yet implemented")
    }

    override suspend fun addInventoryItem(location: InventoryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updateInventoryItem(location: InventoryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInventoryItem(id: SafeUuid) {
        TODO("Not yet implemented")
    }

    override fun getAllStorageLocations(): Flow<List<StorageLocation>> = storageLocations

    override suspend fun getStorageLocationById(id: SafeUuid): StorageLocation?  =
        _storageLocations.value[id]

    override suspend fun addStorageLocation(location: StorageLocation) {
        val newMap = LinkedHashMap(_storageLocations.value)
        newMap[location.id] = location
        _storageLocations.value = newMap
    }

    override suspend fun updateStorageLocation(location: StorageLocation) {
        _storageLocations.value[location.id] = location
    }

    override suspend fun deleteStorageLocation(id: SafeUuid) {
        TODO("Not yet implemented")
    }
}