package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tech.penser.rhinokm.feature.inventory.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageLocationListScreen(
    navController: NavController,
    viewModel: StorageLocationViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.storage_locations_screen_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.arrow_back_description)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.Center) {

            Column {

                LazyColumn {
                    val locations = listOf("Location 1", "Location 2", "Location 3")
                    items(locations.count()) { index ->
                        val storageLocation = locations[index]
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(R.dimen.spacing_small))
                        ) {
                            Text(text = storageLocation)
                            Text(text = "Abb")
                        }
                    }
                }

                Card(modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.spacing_small))
                ) {
                    val addLocationNameHint = stringResource(
                        id = R.string.storage_locations_new_location_name
                    )
                    val addLocationAbbreviationHint = stringResource(
                        id = R.string.storage_locations_new_location_abbr
                    )
                    Row {
                        Column(modifier = modifier.weight(1f)) {
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                modifier = modifier.fillMaxWidth(),
                                singleLine = true,
                                placeholder = { Text(text = addLocationNameHint) }
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                modifier = modifier.fillMaxWidth().padding(top = 0.dp),
                                singleLine = true,
                                placeholder = { Text(text = addLocationAbbreviationHint) }
                            )
                        }
                        Column(
                            modifier = modifier.padding(top = 8.dp, end = 8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.add_new_storage_location)
                            )
                        }
                    }
                }
            }
        }
    }
}