package presentation.screen.balance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.designsystem.MoneyInputText
import presentation.designsystem.PrimaryButton
import presentation.theme.MoneyMateTheme

@Composable
fun BalanceRoute(
    modifier: Modifier = Modifier,
    onProceed: () -> Unit,
) {
    val viewModel = koinViewModel(BalanceViewModel::class)

    BalanceScreen(
        modifier = modifier.fillMaxSize(),
        onProceed = { viewModel.updateUsersBalance(it, onBalanceUpdated = onProceed) },
        onContinueWithoutBalance = onProceed,
    )
}

@Composable
internal fun BalanceScreen(
    modifier: Modifier = Modifier,
    onProceed: (balance: String) -> Unit,
    onContinueWithoutBalance: () -> Unit,
) {
    var balance by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Balance",
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "Let’s talk money",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.h4,
        )

        Text(
            text = "Here is where you can add your total amount. Don’t forget to change for the correct currency.",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
        )

        Spacer(modifier = Modifier.weight(0.3f))

        MoneyInputText(value = balance, onValueChange = { balance = it })

        Spacer(modifier = Modifier.weight(1f))

        val enabled = (balance.isNotEmpty())
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { if (enabled) onProceed(balance) },
            enabled = enabled,
        ) {
            Text(text = "Proceed", style = MaterialTheme.typography.button)
        }

        val interactionSource = remember { MutableInteractionSource() }
        Text(
            text = "Continue without balance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable(interactionSource = interactionSource, indication = null, onClick = onContinueWithoutBalance)
                .padding(top = 4.dp, bottom = 40.dp),
            style = MaterialTheme.typography.caption,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
        )

    }
}


@Preview
@Composable
private fun PreviewBalanceScreen() {
    MoneyMateTheme {
        BalanceScreen(modifier = Modifier.fillMaxSize(), onProceed = { }, onContinueWithoutBalance = { })
    }
}
