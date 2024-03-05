package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import presentation.screen.home.HomeRoute

@Composable
fun navHost(modifier: Modifier = Modifier) {
    val nav = rememberNavigator()
    NavHost(
        modifier = modifier,
        navigator = rememberNavigator(),
        initialRoute = "/home"
    ) {
        scene(route = Route.HOME) {
            HomeRoute(onNewOperation = { /*TODO*/ })
        }
    }
}
