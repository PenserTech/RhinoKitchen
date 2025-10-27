package tech.penser.rhinokm.feature.inventory.domain.model

import tech.penser.rhinokm.core.domain.model.SafeUuid
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Inventory @OptIn(ExperimentalTime::class) constructor(
    val id: SafeUuid,
    val created: Instant = Clock.System.now(), // Capture the current moment
    val createdBy: String,
    val items: List<InventoryItem>
)