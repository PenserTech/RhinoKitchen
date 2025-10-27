package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.domain.repository.InventoryRepository

class StorageLocationsViewModel(repository: InventoryRepository) : ViewModel() {

    val locations = repository.getAllStorageLocations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _editingLocationIdState = mutableStateOf<SafeUuid?>(null)
    val editingLocationIdState: State<SafeUuid?> = _editingLocationIdState

    // Temporary storage for the values being edited
    private var _tempEditName = mutableStateOf("")
    val tempEditNameState: State<String> = _tempEditName
    private var _tempEditAbbr = mutableStateOf("")
    val tempEditAbbrState: State<String> = _tempEditAbbr

}