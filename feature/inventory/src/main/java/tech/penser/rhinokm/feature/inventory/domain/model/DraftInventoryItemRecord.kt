package tech.penser.rhinokm.feature.inventory.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class DraftInventoryItemRecord @OptIn(ExperimentalUuidApi::class) constructor(
    val inventoryItemId: Uuid, // From InventoryItem
    val inventoryItemName: String, // For display
    val storageLocationId: Uuid, // From StorageLocation
    val storageLocationName: String, // For display
    var quantity: Double? = null // User will fill this
)
