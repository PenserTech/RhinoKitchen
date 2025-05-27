package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.penser.rhinokm.feature.inventory.R

@Composable
fun InventoryScreen() {
//    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
//        Text(text = "Inventory Screen")
//        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
//        Text(text = "This screen should provide access to previous inventories, " +
//                "but most importantly, it allows creating new inventories. " +
//                "There are also screens for managing inventory items, storage locations, " +
//                "and item measurement units.")
//    }
    InventoryLandingScreen(
        onStartNewInventory = {},
        onOpenInProgress = {},
        onViewAllInventories = {},
        onManageStorageLocations = {},
        onManageUnits = {},
        onManageInventoryItems = {}
    )
}

@Composable
fun InventoryLandingScreen(
    onStartNewInventory: () -> Unit,
    onOpenInProgress: () -> Unit,
    onViewAllInventories: () -> Unit,
    onManageStorageLocations: () -> Unit,
    onManageUnits: () -> Unit,
    onManageInventoryItems: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onStartNewInventory,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.spacing_normal)),
                text = { Text("Start New Inventory") },
                icon = { Icon(Icons.Default.Add, contentDescription = null) }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.spacing_normal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_normal))
        ) {

            // Card 1: In-progress inventory (conditionally shown)
            item {
                InProgressInventoryCard(
                    onOpen = onOpenInProgress
                )
            }

            // Card 2: Recent inventories
            item {
                RecentInventoriesCard(
                    onViewAll = onViewAllInventories
                )
            }

            // Card 3: Management buttons
            item {
                ManagementButtonsCard(
                    onStorageClick = onManageStorageLocations,
                    onUnitsClick = onManageUnits,
                    onItemsClick = onManageInventoryItems
                )
            }
        }
    }
}

@Composable
fun InProgressInventoryCard(onOpen: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onOpen
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.spacing_normal))) {
            Text(stringResource(R.string.progress))
            Text(stringResource(R.string.tap_continue), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun RecentInventoriesCard(onViewAll: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.spacing_normal))) {
            Text(stringResource(R.string.recent_inventories))
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
            repeat(3) {
                Text("Inventory #${it + 1}")
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
            TextButton(onClick = onViewAll) {
                Text(stringResource(R.string.view_all))
            }
        }
    }
}

@Composable
fun ManagementButtonsCard(
    onStorageClick: () -> Unit,
    onUnitsClick: () -> Unit,
    onItemsClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.spacing_normal))) {
            Text(stringResource(R.string.manage))
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
            Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))) {
                Button(onClick = onItemsClick, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.items))
                }
                Button(onClick = onStorageClick, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.storage))
                }
                Button(onClick = onUnitsClick, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.units))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}

@Preview(showBackground = true)
@Composable
fun RecentInventoriesCardPreview() {
    RecentInventoriesCard(onViewAll = {})
}

@Preview(showBackground = true)
@Composable
fun InProgressInventoryCardPreview() {
    InProgressInventoryCard(onOpen = {})
}

@Preview(showBackground = true)
@Composable
fun ManagementButtonsCardPreview() {
    ManagementButtonsCard(
        onStorageClick = {},
        onUnitsClick = {},
        onItemsClick = {},
    )
}

@Preview(showSystemUi = true)
@Composable
fun InventoryLandingScreenPreview() {
    InventoryLandingScreen(
        onStartNewInventory = {},
        onOpenInProgress = {},
        onViewAllInventories = {},
        onManageStorageLocations = {},
        onManageUnits = {},
        onManageInventoryItems = {}
    )
}

