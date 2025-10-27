
package tech.penser.rhinokm.feature.inventory.data.local.model

import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation

fun StorageLocationEntity.toDomain(): StorageLocation {
    return StorageLocation(
        id = id,
        name = name,
        abbreviation = abbreviation
    )
}

fun StorageLocation.toEntity(): StorageLocationEntity {
    return StorageLocationEntity(
        id = id,
        name = name,
        abbreviation = abbreviation
    )
}
