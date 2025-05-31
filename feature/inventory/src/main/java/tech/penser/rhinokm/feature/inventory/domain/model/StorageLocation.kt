package tech.penser.rhinokm.feature.inventory.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class StorageLocation @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val name: String,
    val abbreviation: String
)
