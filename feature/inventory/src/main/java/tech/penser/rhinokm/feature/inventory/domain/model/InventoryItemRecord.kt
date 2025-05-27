package tech.penser.rhinokm.feature.inventory.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class InventoryItemRecord @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val inventoryId: Uuid,
    val name: String,
    val unit: String, //break link to actual Units since this is "carved in stone"
    val storage: String, //string lets us change storage location entities without changing this
    val quantity: Double,
    val created: Instant,
)
