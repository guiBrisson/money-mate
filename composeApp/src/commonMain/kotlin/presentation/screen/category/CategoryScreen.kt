package presentation.screen.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import domain.model.Category
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.designsystem.CategoryCard
import presentation.designsystem.PrimaryButton
import presentation.designsystem.SearchInputText
import presentation.designsystem.TopAppBar
import presentation.theme.EXTRA_SMALL_PADDING
import presentation.theme.LARGE_PADDING
import presentation.theme.MEDIUM_PADDING
import presentation.theme.MoneyMateTheme


@Composable
fun CategoryRoute(
    onBack: () -> Unit,
) {
    val viewModel = koinViewModel(CategoryViewModel::class)
    CategoryScreen(
        onBack = onBack,
        viewModel = viewModel)
}

@Composable
internal fun CategoryScreen(
    onBack: () -> Unit,
    viewModel: CategoryViewModel
) {

    val filterText by viewModel.filterText.collectAsState()
    val filteredItems by viewModel.filteredItems.collectAsState(initial = Category.all())

    Scaffold(
        topBar = {
            TopAppBar(title = "Categories", onBack = onBack)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = LARGE_PADDING)
        ) {
            item {
                SearchInputText(
                    modifier = Modifier
                        .padding(top = EXTRA_SMALL_PADDING, bottom = MEDIUM_PADDING),
                    value = filterText,
                    label = "Search category...",
                    onValueChange = { newText->
                        viewModel.onFilterTextChanged(newText)
                    })
            }

            itemsIndexed(items = filteredItems) { index, listModel ->
                CategoryCard(listModel)
            }

        }
    }
}

@Composable
@Preview
private fun PreviewCategoryScreen() {
    MoneyMateTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            CategoryScreen(onBack = { }, viewModel = koinViewModel(CategoryViewModel::class))
        }
    }
}