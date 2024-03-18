package presentation.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import presentation.theme.FONT_32
import presentation.theme.FONT_48
import presentation.theme.INPUT_HEIGHT
import presentation.theme.MIN_INPUT_HEIGHT
import presentation.theme.oswaldFontFamily
import utils.MoneyVisualTransformation
import utils.canBeLong


@Composable
private fun BaseInputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    cursorBrush: Brush = SolidColor(MaterialTheme.colors.onSurface),
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        cursorBrush = cursorBrush,
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = MIN_INPUT_HEIGHT)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colors.surface)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            leadingIcon?.invoke()
            Box(modifier = Modifier.defaultMinSize(minHeight = 24.dp).weight(1f)) {
                if (value.isEmpty()) label?.invoke()
                innerTextField()
            }
            trailingIcon?.invoke()
        }
    }
}

@Composable
fun SearchInputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Search...",
) {
    val onSurfaceColor = MaterialTheme.colors.onSurface
    val searchLabel: @Composable () -> Unit = {
        Text(
            text = label,
            style = MaterialTheme.typography.body1.copy(color = onSurfaceColor.copy(0.3f)),
        )
    }
    val leadingIcon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search icon",
            tint = onSurfaceColor.copy(0.3f),
        )
    }
    val trailingIcon: @Composable () -> Unit = {
        AnimatedVisibility(value.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
            val interactionSource = remember { MutableInteractionSource() }
            Icon(
                modifier = Modifier
                    .clickable(interactionSource = interactionSource, indication = null) { onValueChange("") }
                    .pointerHoverIcon(PointerIcon.Hand),
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear icon",
                tint = onSurfaceColor,
            )
        }
    }

    BaseInputText(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(INPUT_HEIGHT),
        singleLine = true,
        label = searchLabel,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
    )
}

@Composable
fun InputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
) {
    val basicLabel: @Composable () -> Unit = {
        Text(
            text = label,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface.copy(0.3f)),
        )
    }

    BaseInputText(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(INPUT_HEIGHT),
        singleLine = true,
        label = basicLabel,
    )
}


@Composable
fun MoneyInputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            text = "$",
            fontFamily = oswaldFontFamily(),
            fontSize = FONT_32,
            fontWeight = FontWeight.Light,
        )

        val textStyle = TextStyle(
            fontSize = FONT_48,
            fontFamily = oswaldFontFamily(),
            color = MaterialTheme.colors.onBackground,
            lineHeight = FONT_48,
        )

        BasicTextField(
            modifier =  textFieldModifier,
            value = value,
            onValueChange = { if (it.canBeLong() || it.isEmpty()) onValueChange(it) },
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = MoneyVisualTransformation(),
            cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "0.00",
                        style = textStyle,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                    )
                }
                innerTextField()
            }
        )
    }
}
