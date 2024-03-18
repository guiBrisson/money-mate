import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import utils.initKoin


fun main() = application {
    initKoin()

    val windowState = rememberWindowState(
        size = DpSize(393.dp, 852.dp)
    )
    
    Window(state = windowState,onCloseRequest = ::exitApplication, title = "MoneyMate") {
        App()
    }
}
