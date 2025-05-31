package tech.penser.rhinokm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.penser.rhinokm.domain.navigation.NavigationScaffold
import tech.penser.rhinokm.ui.theme.RhinoKMTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RhinoKMTheme(darkTheme = true) {
                NavigationScaffold()
            }
        }
    }
}

@Composable
fun ColorTestSurface() {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.spacing_large)),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.spacing_normal)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                Text("Primary: ${MaterialTheme.colorScheme.primary}",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.secondary)) {
                Text("Secondary: ${MaterialTheme.colorScheme.secondary}",
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)) {
                Text("Tertiary: ${MaterialTheme.colorScheme.tertiary}",
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Theme")
@Composable
fun LightThemePreview() {
    RhinoKMTheme(darkTheme = false) {
        ColorTestSurface()
    }
}

@Preview(showBackground = true, name = "Dark Theme")
@Composable
fun DarkThemePreview() {
    RhinoKMTheme(darkTheme = true) {
        ColorTestSurface()
    }
}

@Composable
fun HomeScreen() {
    RhinoKMTheme(darkTheme = true) {
        ColorTestSurface()
    }
}
