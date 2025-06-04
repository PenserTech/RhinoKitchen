package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class StorageLocationsViewModel : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    val locations = mutableStateListOf<StorageLocation>()

    private val _editingLocationIdState = mutableStateOf<Uuid?>(null)
    val editingLocationIdState: State<Uuid?> = _editingLocationIdState

    // Temporary storage for the values being edited
    private var _tempEditName = mutableStateOf("")
    val tempEditNameState: State<String> = _tempEditName
    private var _tempEditAbbr = mutableStateOf("")
    val tempEditAbbrState: State<String> = _tempEditAbbr

    init {
        locations.addAll(listOf(
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
        ))
    }
}