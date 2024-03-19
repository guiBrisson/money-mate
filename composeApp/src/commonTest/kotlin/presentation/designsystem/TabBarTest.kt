package presentation.designsystem

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import domain.model.OperationType
import presentation.theme.expense
import presentation.theme.income
import kotlin.test.Test

class TabBarTest {

    private val tabContentColors = listOf(expense, income)
    private val tabItems = OperationType.operationNames()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `Perform click on first tab item`() = runComposeUiTest {
        var selectedItem = ""

        setContent {
            TabBar(
                pages = tabItems,
                contentColors = tabContentColors
            ) { selectedItem = it }
        }
        onNodeWithText(tabItems[0]).performClick()
        onNodeWithText(tabItems[0]).assertTextEquals(selectedItem)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `Perform click on second tab item`() = runComposeUiTest {
        var selectedItem = ""

        setContent {

            TabBar(
                pages = tabItems,
                contentColors = tabContentColors
            ) { selectedItem = it }
        }
        onNodeWithText(tabItems[1]).performClick()
        onNodeWithText(tabItems[1]).assertTextEquals(selectedItem)
    }
}