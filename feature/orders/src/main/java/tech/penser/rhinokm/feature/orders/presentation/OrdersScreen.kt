package tech.penser.rhinokm.feature.orders.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import tech.penser.rhinokm.feature.orders.R

@Composable
fun OrdersScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.spacing_normal)), verticalArrangement = Arrangement.Top) {
        Text(
            text = "Orders Screen",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
        Text(
            text = "This screen should provide access to suppliers, " +
                    "products, and in particular orders./n Most of the time, " +
                    "this screen will be used to create new orders, " +
                    "and occasionally view previous orders. Very rarely, " +
                    "there might be need to view the list of suppliers and the products they provide. " +
                    "It might be needed to provide a searchable list of orders and and " +
                    "a searchable list products from all suppliers.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
