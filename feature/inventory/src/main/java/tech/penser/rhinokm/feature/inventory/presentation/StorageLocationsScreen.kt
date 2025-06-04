package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tech.penser.rhinokm.feature.inventory.R
import tech.penser.rhinokm.feature.inventory.domain.model.StorageLocation
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun StorageLocationsScreen(
    navController: NavController,
    viewModel: StorageLocationsViewModel,
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            var editingItemId by rememberSaveable { mutableStateOf<Uuid?>(null) }
            var editingName by rememberSaveable { mutableStateOf("") }
            var editingAbbr by rememberSaveable { mutableStateOf("") }

            fun startEditing(location: StorageLocation) {
                editingItemId = location.id
                editingName = location.name
                editingAbbr = location.abbreviation
            }

            fun cancelEditing() {
                editingItemId = null
                editingName = ""
                editingAbbr = ""
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .testTag("locations_list"),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = viewModel.locations,
                    key = { location -> location.id }
                ) { location ->
                    val isEditing = editingItemId == location.id
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
                    ) {
                        Row {

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                                .weight(1.0f)
                        ) {
                            if (isEditing) {
                                OutlinedTextField(
                                    value = editingName,
                                    onValueChange = { editingName = it },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                                OutlinedTextField(
                                    value = editingAbbr,
                                    onValueChange = { editingAbbr = it },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            } else {
                                Text(
                                    text = location.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = location.abbreviation,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_large)))
                        if (isEditing) {
                            IconButton(onClick = { cancelEditing() }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = stringResource(
                                        id = R.string.cancel_edit_storage_location
                                    )
                                )
                            }
                            IconButton(onClick = {
                                // TODO: Save logic
                                cancelEditing()
                            }) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = stringResource(
                                        id = R.string.save_storage_location
                                    )
                                )
                            }
                        } else {
                            IconButton(onClick = { startEditing(location) }) {
                                Icon(Icons.Default.Edit,
                                    contentDescription = stringResource(
                                        id = R.string.edit_storage_location
                                    ))
                            }
                        }
                    }
                }

            }
                        }
            // Add New Item Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(stringResource(R.string.storage_locations_new_location_name)) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(stringResource(R.string.storage_locations_new_location_abbr)) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )

                    }
                    Spacer(modifier = Modifier.width(36.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add_new_storage_location),
                            modifier = Modifier.size(dimensionResource(R.dimen.spacing_large))
                        )
                    }
                }
            }
        }
    }
}
