package presentation.screen.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import domain.model.Category
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.designsystem.CategoryCard
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
    CategoryScreen(onBack = onBack)
}

@Composable
internal fun CategoryScreen(
    onBack: () -> Unit,
) {

    val getAllCategory = Category.all()

    var category by remember {
        mutableStateOf("")
    }

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
                    value = category,
                    label = "Search category...",
                    onValueChange = {
                        category = it
                    })
            }

            itemsIndexed(items = getAllCategory) { index, listModel ->
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
            CategoryScreen(onBack = { })
        }
    }
}