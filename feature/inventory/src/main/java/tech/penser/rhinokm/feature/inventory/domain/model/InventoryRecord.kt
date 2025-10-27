package tech.penser.rhinokm.feature.inventory.domain.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class InventoryRecord @OptIn( ExperimentalTime::class) constructor(
    val id: Uuid,
    val created: Instant,
    val createdBy: String,
    val items: List<InventoryItemRecord>
)