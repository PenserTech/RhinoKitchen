package tech.penser.rhinokm.feature.inventory.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.domain.model.InventoryItem
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class InventoryRepositoryImpl : InventoryRepository {

    private val _locations = MutableStateFlow<List<StorageLocation>>(emptyList())
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

    override fun getAllStorageLocations(): Flow<List<StorageLocation>> = _locations.asStateFlow()

    override suspend fun getStorageLocationById(id: SafeUuid): StorageLocation? {
        return _locations.value.find { it.id == id }
    }

    override suspend fun addStorageLocation(location: StorageLocation) {
        _locations.value = _locations.value + location
    }

    override suspend fun updateStorageLocation(location: StorageLocation) {
        _locations.value = _locations.value.map { 
            if (it.id == location.id) location else it
        }
    }

    override suspend fun deleteStorageLocation(id: SafeUuid) {
        _locations.value = _locations.value.filter { it.id != id }
    }
}