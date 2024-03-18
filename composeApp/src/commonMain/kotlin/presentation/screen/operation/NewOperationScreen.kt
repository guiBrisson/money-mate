package presentation.screen.operation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.Category
import domain.model.OperationType
import moe.tlaster.precompose.koin.koinViewModel
import moneymate.composeapp.generated.resources.Res
import moneymate.composeapp.generated.resources.ic_arrow_right
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.designsystem.InputText
import presentation.designsystem.MoneyInputText
import presentation.designsystem.PrimaryButton
import presentation.designsystem.TabBar
import presentation.designsystem.TopAppBar
import presentation.theme.EXTRA_SMALL_PADDING
import presentation.theme.FONT_14
import presentation.theme.FONT_16
import presentation.theme.INPUT_HEIGHT
import presentation.theme.LARGE_PADDING
import presentation.theme.MEDIUM_PADDING
import presentation.theme.MoneyMateTheme
import presentation.theme.SMALL_PADDING
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

@OptIn(ExperimentalResourceApi::class)
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

    var category: Category? by rememberSaveable {
        mutableStateOf(Category.DINING_OUT)
    }

    var selectedOperation by rememberSaveable {
        mutableStateOf(OperationType.EXPENSE)
    }

    var isMonthlyOperation by rememberSaveable {
        mutableStateOf(false)
    }

    val tabContentColors = listOf(expense, income)
    val operations = listOf(OperationType.EXPENSE.operation, OperationType.INCOME.operation)

    //TODO: Remover esta lista ao implementar lógica de navegação para tela de categorias
    val categories = listOf(
        Category.TRAVEL,
        Category.DINING_OUT,
        Category.HEALTH_WELLNESS,
        Category.EDUCATION,
        Category.ENTERTAINMENT,
        Category.GIFTS_DONATIONS,
        Category.GROCERIES,
        Category.INCOME,
        Category.MISCELLANEOUS,
        Category.TRANSPORT,
        Category.SUBSCRIPTION
    )

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
                value = operationAmount,
                onValueChange = { operationAmount = it }
            )

            Text(modifier = Modifier
                .padding(top = LARGE_PADDING),
                text = "Description")

            InputText(
                modifier = Modifier
                    .padding(top = EXTRA_SMALL_PADDING),
                value = description,
                label = "e.g Hamburger from Bobs",
                onValueChange = {
                    description = it
                }
            )

            Text(
                modifier = Modifier
                    .padding(top = LARGE_PADDING),
                text = "Category"
            )

            Selector(
                modifier = Modifier
                    .padding(top = EXTRA_SMALL_PADDING)
                    .fillMaxWidth(),
                icon = category?.icon,
                iconTint = category?.primaryColor,
                value = category?.categoryName,
                label = "category"
            ) {
                //TODO: Call Categories Screen
                //Remover este código ao implementar navegação para tela de categorias
                //OBS: Tela de categorias precisa ter um callback para atualizar a category
                //desta tela. Remover também a lista categories
                val randomInt = (0..9).random()
                category = categories[randomInt]
            }

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

            Spacer(Modifier.weight(1f))

            val enabled = operationAmount.isNotEmpty() &&
                    description.isNotEmpty() && category != null

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MEDIUM_PADDING),
                onClick = {  },
                enabled = enabled,
            ) {
                Text(text = "Done", style = MaterialTheme.typography.button)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Selector(modifier: Modifier = Modifier, icon: DrawableResource? = null,
             iconTint: Color? = null, value: String? = null, label: String,
             onSelectorClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(INPUT_HEIGHT)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.surface)
            .padding(SMALL_PADDING)
            .clickable { onSelectorClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        icon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = "Category Icon",
                tint = iconTint ?: Color.White
            )
        }

        value?.let {
            Text(
                text = it,
                fontSize = FONT_16
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            text = "Select a $label",
            fontSize = FONT_14,
            color = Color.White.copy(alpha = 0.6f)
        )

        Icon(
            painterResource(Res.drawable.ic_arrow_right),
            contentDescription = null
        )
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
