package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import tech.penser.rhinokm.feature.inventory.R
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun InventoryListScreen() {
    Column(modifier = Modifier) {
        Text(text = "Inventory List Screen")
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
    }
}