package tech.penser.rhinokm.feature.inventory.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tech.penser.rhinokm.core.domain.model.SafeUuid

@Entity(tableName = "measurement_units")
data class MeasurementUnitEntity(
    @PrimaryKey
    val id: SafeUuid = SafeUuid.random(),
    val name: String,
    val abbreviation: String
)