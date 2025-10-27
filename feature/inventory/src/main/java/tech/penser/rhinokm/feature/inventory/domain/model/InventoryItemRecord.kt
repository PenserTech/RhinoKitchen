package tech.penser.rhinokm.feature.inventory.domain.model

import tech.penser.rhinokm.core.domain.model.SafeUuid
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class InventoryItemRecord @OptIn(ExperimentalTime::class) constructor(
    val id: SafeUuid,
    val name: String,
    val unit: String, //break link to actual Units since this is "carved in stone"
    val storage: String, //string lets us change storage location entities without changing this
    val quantity: Double,
    val created: Instant,
)
