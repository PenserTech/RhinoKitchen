package tech.penser.rhinokm.feature.inventory.domain.model

import tech.penser.rhinokm.core.domain.model.SafeUuid

data class MeasurementUnit (
    val id: SafeUuid = SafeUuid.random(),
    val name: String,
    val abbreviation: String
)
