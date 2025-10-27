package tech.penser.rhinokm.feature.inventory.domain.model

import tech.penser.rhinokm.core.domain.model.SafeUuid
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class StorageLocation (
    val id: SafeUuid,
    val name: String,
    val abbreviation: String
)
