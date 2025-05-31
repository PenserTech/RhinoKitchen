package tech.penser.rhinokm.feature.inventory.presentation

import androidx.lifecycle.ViewModel
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class StorageLocationViewModel : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    val locations = listOf(
        StorageLocation(Uuid.random(), "Location 1", "Loc 1"),
        StorageLocation(Uuid.random(), "Location 2", "Loc 2"),
        StorageLocation(Uuid.random(), "Location 3", "Loc 3")

    )
}