package tech.penser.rhinokm.feature.inventory.data.local

import androidx.room.TypeConverter
import tech.penser.rhinokm.core.domain.model.SafeUuid
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class Converters {
    @TypeConverter
    fun fromUuid(uuid: SafeUuid): String = uuid.toString()

    @OptIn(ExperimentalUuidApi::class)
    @TypeConverter
    fun toUuid(uuid: String): SafeUuid = SafeUuid(Uuid.parse(uuid))
}
