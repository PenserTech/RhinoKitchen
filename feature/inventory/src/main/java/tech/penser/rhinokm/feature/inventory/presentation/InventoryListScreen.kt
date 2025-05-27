package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import tech.penser.rhinokm.feature.inventory.R
import tech.penser.rhinokm.feature.inventory.domain.model.Inventory
import tech.penser.rhinokm.feature.inventory.domain.model.InventoryItemRecord
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun InventoryListScreen() {
    val items = listOf(
        InventoryItemRecord(id = "1", name = "Inventory 1"),
        Inventory(id = "2", name = "Inventory 2"),
        Inventory(id = "3", name = "Inventory 3")
    )

    val inventories = listOf(
        Inventory(id = "1", createdBy = "Inventory 1"),
        Inventory(id = "2", name = "Inventory 2"),
        Inventory(id = "3", name = "Inventory 3")
    )
    Column(modifier = Modifier) {
        Text(text = "Inventory List Screen")
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
    }
}