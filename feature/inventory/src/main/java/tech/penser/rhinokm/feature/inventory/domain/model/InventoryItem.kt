package tech.penser.rhinokm.feature.inventory.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class InventoryItem @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val name: String,
    val unit: MeasurementUnit,
    val storage: List<StorageLocation>,
    val minimumStockLevel: Double,
)
