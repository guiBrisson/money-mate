package presentation.designsystem

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import presentation.screen.operation.CustomSwitch
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomSwitchTest {
    private val contentDescription = "True or false selector"

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `Assert switch is true or false when perform two clicks`() = runComposeUiTest {
        var selected = false
        setContent {
            CustomSwitch {
                selected = it
            }
        }

        onNodeWithContentDescription(contentDescription).assertExists()
        onNodeWithContentDescription(contentDescription).performClick()
        assertEquals(selected, true)
        onNodeWithContentDescription(contentDescription).performClick()
        assertEquals(selected, false)
    }
}