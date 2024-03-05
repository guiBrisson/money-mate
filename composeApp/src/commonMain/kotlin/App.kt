import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.navigation.navHost
import presentation.theme.MoneyMateTheme


@Composable
@Preview
fun App() {
    initKoin()

    MoneyMateTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            PreComposeApp {
                navHost()
            }
        }
    }
}
