package tech.penser.rhinokm.feature.inventory.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
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
                modifier = modifier
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.Center) {

            Column {
                Card(modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.spacing_normal))
                ) {
                    val addLocationNameHint = stringResource(
                        id = R.string.storage_locations_new_location_name
                    )
                    Column {
                        TextField(
                            value = "",
                            onValueChange = { },
                            modifier = modifier.fillMaxWidth(),
                            placeholder = { Text(text = addLocationNameHint) }
                        )
                        Text(text = "Storage Location Abbreviation")
                    }

                }
                Row(
                ) {
                }
            }
        }
    }
}