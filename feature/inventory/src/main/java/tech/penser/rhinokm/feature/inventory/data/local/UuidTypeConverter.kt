@file:OptIn(ExperimentalUuidApi::class)

package tech.penser.rhinokm.feature.inventory.data.local

import androidx.room.TypeConverter
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class UuidTypeConverter {
    @TypeConverter
    fun fromUuid(uuid: Uuid): String {
        return uuid.toString()
    }

    @TypeConverter
    fun toUuid(uuidString: String): Uuid {
        return Uuid.parse(uuidString)
    }
}
