package tech.penser.rhinokm.feature.inventory.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class InventoryRecord @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val created: Instant,
    val createdBy: String,
    val items: List<InventoryItemRecord>
)