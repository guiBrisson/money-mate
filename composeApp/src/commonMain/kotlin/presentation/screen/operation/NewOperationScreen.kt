package presentation.screen.operation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.theme.MoneyMateTheme

@Composable
fun NewOperationRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    val viewModel = koinViewModel(NewOperationViewModel::class)
    NewOperationScreen(modifier = modifier, onBack = onBack)
}

@Composable
internal fun NewOperationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize().padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(modifier = Modifier.offset(x = (-16).dp), onClick = onBack) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Arrow back")
            }

            Text(text = "New operation")
        }
    }
}

@Composable
@Preview
private fun PreviewNewOperationScreen() {
    MoneyMateTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            NewOperationScreen(onBack = { })
        }
    }
} 
