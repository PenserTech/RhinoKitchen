@file:OptIn(ExperimentalUuidApi::class)

package tech.penser.rhinokm.feature.inventory.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tech.penser.rhinokm.core.domain.model.SafeUuid
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "storage_locations")
data class StorageLocationEntity(
    @PrimaryKey
    val id: SafeUuid = SafeUuid.random(),
    val name: String,
    val abbreviation: String
)