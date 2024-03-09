package presentation.designsystem

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import kotlin.test.Test


@OptIn(ExperimentalTestApi::class)
class ButtonsTest {

    @Test
    fun `PrimaryButton perform click`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("Hello") }

            Text(modifier = Modifier.testTag("text"), text = text)

            PrimaryButton(modifier = Modifier.testTag("primaryButton"), onClick = { text = "Compose" }) {
                Text(text = "Click me")
            }
        }
        onNodeWithTag("text").assertTextEquals("Hello")
        onNodeWithTag("primaryButton").performClick()
        onNodeWithTag("text").assertTextEquals("Compose")
    }

    @Test
    fun `PrimaryButton assert content`() = runComposeUiTest {
        setContent {
            PrimaryButton(onClick = { }) {
                Text(modifier = Modifier.testTag("contentText"), text = "Click me")
            }
        }
        onNodeWithTag("contentText", useUnmergedTree = true).assertExists()
    }

    @Test
    fun `SecondaryButton perform click`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("Hello") }

            Text(modifier = Modifier.testTag("text"), text = text)

            SecondaryButton(modifier = Modifier.testTag("secondaryButton"), onClick = { text = "Compose" }) {
                Text(text = "Click me")
            }
        }
        onNodeWithTag("text").assertTextEquals("Hello")
        onNodeWithTag("secondaryButton").performClick()
        onNodeWithTag("text").assertTextEquals("Compose")
    }

    @Test
    fun `SecondaryButton assert content`() = runComposeUiTest {
        setContent {
            SecondaryButton(onClick = { }) {
                Text(modifier = Modifier.testTag("contentText"), text = "Click me")
            }
        }
        onNodeWithTag("contentText", useUnmergedTree = true).assertExists()
    }

    @Test
    fun `FloatingButton perform click`() = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf("Hello") }

            Text(modifier = Modifier.testTag("text"), text = text)

            FloatingButton(
                modifier = Modifier.testTag("floatingButton"),
                onClick = { text = "Compose" },
                imageVector = Icons.Default.Add,
                contentDescription = "Floating button test"
            )
        }

        onNodeWithTag("text").assertTextEquals("Hello")
        onNodeWithTag("floatingButton").performClick()
        onNodeWithTag("text").assertTextEquals("Compose")
    }

    @Test
    fun `FloatingButton assert image vector`() = runComposeUiTest {
        setContent {
            FloatingButton(
                modifier = Modifier.testTag("floatingButton"),
                onClick = { },
                imageVector = Icons.Default.Add,
                contentDescription = "Floating button test"
            )
        }
        onNodeWithContentDescription("Floating button test", useUnmergedTree = true).assertExists()
    }

}
