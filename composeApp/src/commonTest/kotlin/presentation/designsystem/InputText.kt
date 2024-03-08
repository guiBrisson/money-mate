package presentation.designsystem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class InputText {

    @Test
    fun `SearchInputText input value update`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("") }
            SearchInputText(
                modifier = Modifier.testTag("searchInputText"),
                value = text,
                onValueChange = { text = it },
            )
        }

        onNodeWithTag("searchInputText").performTextInput("test")
        onNodeWithTag("searchInputText").assert(hasText("test"))
    }

    @Test
    fun `SearchInputText has search Icon`() = runComposeUiTest {
        setContent {
            SearchInputText(
                modifier = Modifier.testTag("searchInputText"),
                value = "",
                onValueChange = { },
            )
        }

        onNodeWithContentDescription("Search icon").assertExists()
    }

    @Test
    fun `SearchInputText clear icon visible only when value is not empty`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("") }
            SearchInputText(
                modifier = Modifier.testTag("searchInputText"),
                value = text,
                onValueChange = { text = it },
            )
        }

        onNodeWithContentDescription("Clear icon", useUnmergedTree = true).assertDoesNotExist()
        onNodeWithTag("searchInputText").performTextInput("test")
        onNodeWithContentDescription("Clear icon", useUnmergedTree = true).assertExists()
        onNodeWithContentDescription("Clear icon", useUnmergedTree = true).performClick()
        onNodeWithContentDescription("Clear icon", useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun `SearchInputText on clear value`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("test") }
            SearchInputText(
                modifier = Modifier.testTag("searchInputText"),
                value = text,
                onValueChange = { text = it },
            )
        }

        onNodeWithTag("searchInputText").assert(hasText("test"))
        onNodeWithContentDescription("Clear icon", useUnmergedTree = true).performClick()
        onNodeWithTag("searchInputText").assert(hasText(""))
    }

    @Test
    fun `InputText input value update`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("") }
            SearchInputText(
                modifier = Modifier.testTag("inputText"),
                value = text,
                onValueChange = { text = it },
            )
        }

        onNodeWithTag("inputText").performTextInput("test")
        onNodeWithTag("inputText").assert(hasText("test"))
    }
}
