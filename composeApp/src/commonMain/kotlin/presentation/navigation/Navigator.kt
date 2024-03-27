package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator
import presentation.screen.balance.BalanceRoute
import presentation.screen.category.CategoryRoute
import presentation.screen.home.HomeRoute
import presentation.screen.operation.NewOperationRoute

@Composable
fun navHost(modifier: Modifier = Modifier, initialRoute: String) {
    val nav = rememberNavigator()
    NavHost(
        modifier = modifier,
        navigator = rememberNavigator(),
        initialRoute = initialRoute,
    ) {
        scene(route = Route.BALANCE) {
            BalanceRoute(onProceed = { nav.navigate(Route.HOME, NavOptions(popUpTo = PopUpTo.First())) })
        }

        scene(route = Route.HOME) {
            HomeRoute(onNewOperation = { nav.navigate(Route.NEW_OPERATION) })
        }

        scene(route = Route.NEW_OPERATION) {
            NewOperationRoute(
                onBack = { nav.goBack() },
                onSelectCategory = { nav.navigate(Route.CATEGORY) })
        }

        scene(route = Route.CATEGORY) {
            CategoryRoute(onBack = { nav.goBack() } )
        }
    }
}
