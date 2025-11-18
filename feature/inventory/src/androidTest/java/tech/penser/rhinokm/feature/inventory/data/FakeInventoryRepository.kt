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

    private val _storageLocations = MutableStateFlow<LinkedHashMap<SafeUuid, StorageLocation>>(linkedMapOf())
    private val storageLocations: Flow<List<StorageLocation>> = _storageLocations.asStateFlow().map { it.values.toList() }

    private val _inventoryItems = MutableStateFlow<LinkedHashMap<SafeUuid, InventoryItem>>(linkedMapOf())
    private val inventoryItems: Flow<List<InventoryItem>> = _inventoryItems.asStateFlow().map { it.values.toList() }

    override fun getAllInventoryItems(): Flow<List<InventoryItem>> = inventoryItems

    override suspend fun getInventoryItemById(id: SafeUuid): InventoryItem? = _inventoryItems.value[id]

    override suspend fun addInventoryItem(item: InventoryItem) {
        val newMap = LinkedHashMap(_inventoryItems.value)
        newMap[SafeUuid(item.id)] = item
        _inventoryItems.value = newMap
    }

    override suspend fun updateInventoryItem(item: InventoryItem) {
        val newMap = LinkedHashMap(_inventoryItems.value)
        newMap[SafeUuid(item.id)] = item
        _inventoryItems.value = newMap
    }

    override suspend fun deleteInventoryItem(id: SafeUuid) {
        val newMap = LinkedHashMap(_inventoryItems.value)
        newMap.remove(id)
        _inventoryItems.value = newMap
    }

    override fun getAllStorageLocations(): Flow<List<StorageLocation>> = storageLocations

    override suspend fun getStorageLocationById(id: SafeUuid): StorageLocation? = _storageLocations.value[id]

    override suspend fun addStorageLocation(location: StorageLocation) {
        val newMap = LinkedHashMap(_storageLocations.value)
        newMap[location.id] = location
        _storageLocations.value = newMap
    }

    override suspend fun updateStorageLocation(location: StorageLocation) {
        val newMap = LinkedHashMap(_storageLocations.value)
        newMap[location.id] = location
        _storageLocations.value = newMap
    }

    override suspend fun deleteStorageLocation(id: SafeUuid) {
        val newMap = LinkedHashMap(_storageLocations.value)
        newMap.remove(id)
        _storageLocations.value = newMap
    }

    // Helper function for tests to pre-populate data
    fun seedStorageLocations(locations: List<StorageLocation>) {
        val newMap = LinkedHashMap<SafeUuid, StorageLocation>()
        locations.forEach { newMap[it.id] = it }
        _storageLocations.value = newMap
    }

    // Helper function for tests to pre-populate data
    fun seedInventoryItems(items: List<InventoryItem>) {
        val newMap = LinkedHashMap<SafeUuid, InventoryItem>()
        items.forEach { newMap[SafeUuid(it.id)] = it }
        _inventoryItems.value = newMap
    }
}
