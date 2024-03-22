package presentation.designsystem

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import domain.model.UIState
import kotlinx.coroutines.delay
import presentation.theme.CORNER_RADIUS_22
import presentation.theme.CORNER_RADIUS_4
import presentation.theme.MIN_INPUT_HEIGHT
import presentation.theme.PADDING_16
import presentation.theme.PADDING_2
import presentation.theme.SNACK_BAR_DURATION
import presentation.theme.ZERO_DP
import presentation.theme.green600

@Composable
fun CustomSnackBar(modifier: Modifier, state: UIState<Any>, onActionClick: (closeScreen: Boolean) -> Unit) {
    val transition = updateTransition(
        targetState = state,
        label = "main transition",
    )

    val alphaContent by transition.animateFloat { uiState ->
        when(uiState) {
            is UIState.Loading, UIState.Empty -> 0f
            else -> 1f
        }
    }

    val horizontalContentPadding by transition.animateDp(
        targetValueByState = { uiState ->
            when(uiState) {
                is UIState.Loading, UIState.Empty -> ZERO_DP
                else -> PADDING_16
            }
        },
        label = "snack's content padding",
    )

    val shapeContent by transition.animateDp(
        targetValueByState = { uiState ->
            when(uiState) {
                is UIState.Loading, UIState.Empty -> CORNER_RADIUS_22
                else -> CORNER_RADIUS_4
            }
        },
        label = "button's content padding",
    )

    val widthContent by transition.animateFloat { uiState ->
        when(uiState) {
            is UIState.Loading, UIState.Empty -> 0f
            else -> 1f
        }
    }

    val backgroundColor: Color =
        when(state) {
            is UIState.Error -> Color.Red
            is UIState.Success -> green600
            else -> Color.LightGray
        }

    Row (
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(shapeContent)
            )
            .defaultMinSize(minHeight = MIN_INPUT_HEIGHT)
            .fillMaxWidth(fraction = widthContent)
            .animateContentSize()
            .alpha(alphaContent)
            .padding(PaddingValues(horizontal = horizontalContentPadding)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = (state as? UIState.Error)?.error?.message ?: "Sucesso!",
            style = MaterialTheme.typography.button,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (state is UIState.Error) {
            Spacer(Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .clickable {
                        onActionClick.invoke(false)
                    }
                    .padding(PADDING_2),
                text = "OK",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

    if (state is UIState.Success) {
        LaunchedEffect(key1 = Unit){
            delay(SNACK_BAR_DURATION)
            onActionClick.invoke(true)
        }
    }
}