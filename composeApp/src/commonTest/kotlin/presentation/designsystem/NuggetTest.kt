package presentation.designsystem

import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class NuggetTest {

    @Test
    fun `FilterNugget perform click`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("Hello") }

            Text(modifier = Modifier.testTag("text"), text = text)

            FilterNugget(modifier = Modifier.testTag("filterNugget"), label = "test", onClick = { text = "Compose" })
        }
        onNodeWithTag("text").assertTextEquals("Hello")
        onNodeWithTag("filterNugget").performClick()
        onNodeWithTag("text").assertTextEquals("Compose")
    }

    @Test
    fun `FilterNugget selected text over label`()  = runComposeUiTest {
        setContent {
            FilterNugget(
                modifier = Modifier.testTag("filterNugget"),
                label = "label",
                selected = "selected",
                onClick = { },
            )
        }
        onNodeWithTag("filterNugget").assertTextEquals("selected")
    }

}
