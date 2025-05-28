package tech.penser.rhinokm.feature.inventory.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Inventory @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val created: Instant = Clock.System.now(), // Capture the current moment
    val createdBy: String,
    val items: List<InventoryItem>
)