package presentation.screen.operation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import domain.model.Category
import domain.model.OperationType
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.designsystem.InputText
import presentation.designsystem.MoneyInputText
import presentation.designsystem.TabBar
import presentation.designsystem.TopAppBar
import presentation.theme.SMALL_PADDING
import presentation.theme.LARGE_PADDING
import presentation.theme.MEDIUM_PADDING
import presentation.theme.MoneyMateTheme
import presentation.theme.border
import presentation.theme.expense
import presentation.theme.income

@Composable
fun NewOperationRoute(
    onBack: () -> Unit,
) {
    val viewModel = koinViewModel(NewOperationViewModel::class)
    NewOperationScreen(onBack = onBack)
}

@Composable
internal fun NewOperationScreen(
    onBack: () -> Unit,
) {
    var operationAmount by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var category by rememberSaveable {
        mutableStateOf(Category.HEALTH_WELLNESS)
    }

    var selectedOperation by rememberSaveable {
        mutableStateOf(OperationType.EXPENSE)
    }

    var isMonthlyOperation by rememberSaveable {
        mutableStateOf(false)
    }

    val tabContentColors = listOf(expense, income)
    val operations = listOf(OperationType.EXPENSE.operation, OperationType.INCOME.operation)

    Scaffold(
        topBar = {
            TopAppBar(title = "New Operation", onBack = onBack)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = LARGE_PADDING)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TabBar(
                modifier = Modifier
                    .padding(vertical = LARGE_PADDING),
                pages = operations,
                contentColors = tabContentColors
            ) { selectedItem ->
                selectedOperation = OperationType.valueOf(selectedItem.uppercase())
            }

            Text("Add ${selectedOperation.operation.lowercase()}")

            MoneyInputText(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING),
                value = operationAmount,
                onValueChange = { operationAmount = it }
            )

            Text("Description")

            InputText(
                modifier = Modifier
                    .padding(top = SMALL_PADDING),
                value = description,
                onValueChange = {
                    description = it
                }
            )

            Text(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING),
                text = "Category"
            )

            InputText(
                modifier = Modifier
                    .padding(top = SMALL_PADDING),
                value = category.categoryName,
                onValueChange = {
                    category = Category.valueOf(it.uppercase())
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MEDIUM_PADDING),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Is this a monthly operation?")

                CustomSwitch {
                    isMonthlyOperation = it
                }
            }
        }
    }
}

@Composable
fun CustomSwitch(onChecked: (Boolean) -> Unit) {

    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }

    Switch(
        checked = isChecked,
        onCheckedChange = { checked ->
            isChecked = checked
            onChecked(isChecked)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.primary,
            checkedTrackColor = MaterialTheme.colors.surface,
            uncheckedThumbColor = border,
            uncheckedTrackColor = MaterialTheme.colors.surface
        )
    )
}

@Composable
@Preview
private fun PreviewNewOperationScreen() {
    MoneyMateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewOperationScreen(onBack = { })
        }
    }
} 
