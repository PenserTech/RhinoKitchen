package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class StorageLocationsViewModel : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    val locations = mutableStateListOf<StorageLocation>(
        StorageLocation(Uuid.random(), "Location 1", "L1"),
        StorageLocation(Uuid.random(), "Location 2", "L2"),
        StorageLocation(Uuid.random(), "Location 3", "L3"),
        StorageLocation(Uuid.random(), "Location 4", "L4"),
        StorageLocation(Uuid.random(), "Location 5", "L5"),
        StorageLocation(Uuid.random(), "Location 6", "L6"),
        StorageLocation(Uuid.random(), "Location 7", "L7"),
        StorageLocation(Uuid.random(), "Location 8", "L8"),
        StorageLocation(Uuid.random(), "Location 9", "L9"),
        StorageLocation(Uuid.random(), "Location 10", "L10"),
        StorageLocation(Uuid.random(), "Location11", "L11"),
    )

}