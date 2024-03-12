import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.PreComposeApp
import presentation.navigation.Route
import presentation.navigation.navHost
import presentation.theme.MoneyMateTheme
import utils.onboardPreference


@Composable
fun App() {
    initKoin()

    MoneyMateTheme {
        val onboardDone by onboardPreference()
        val route = if (onboardDone) Route.HOME else Route.BALANCE

        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            PreComposeApp {
                navHost(initialRoute = route)
            }
        }
    }
}
