@file:OptIn(ExperimentalUuidApi::class)

package tech.penser.rhinokm.feature.inventory.data.local

import StorageLocationDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.penser.rhinokm.feature.inventory.data.local.model.MeasurementUnitEntity
import tech.penser.rhinokm.feature.inventory.data.local.model.StorageLocationEntity
import kotlin.uuid.ExperimentalUuidApi

@Database(entities = [StorageLocationEntity::class, MeasurementUnitEntity::class], version = 1)
@TypeConverters(UuidTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storageLocationDao(): StorageLocationDao

    abstract fun measurementUnitDao(): MeasurementUnitDao
}
