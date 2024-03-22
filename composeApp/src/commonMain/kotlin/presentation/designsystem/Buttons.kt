package presentation.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import domain.model.UIState
import presentation.theme.BUTTON_PROGRESS_HEIGHT
import presentation.theme.CORNER_RADIUS_22
import presentation.theme.CORNER_RADIUS_4
import presentation.theme.PADDING_12
import presentation.theme.PADDING_16
import presentation.theme.PADDING_24
import presentation.theme.PADDING_28
import presentation.theme.ZERO_DP

@Composable
private fun BaseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    innerPadding: PaddingValues,
    shape: Shape = RoundedCornerShape(CORNER_RADIUS_4),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        contentPadding = innerPadding,
        shape = shape,
        content = content,
    )
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
        enabled = enabled,
        innerPadding = PaddingValues(PADDING_12),
        content = content,
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
        ),
        enabled = enabled,
        innerPadding = PaddingValues(PADDING_12),
        content = content,
    )
}

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
) {
    BaseButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
        shape = CircleShape,
        innerPadding = PaddingValues(PADDING_16),
    ) {
        Icon(modifier = Modifier.size(PADDING_28), imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun LoadingButton(modifier: Modifier, text: String, enabled: Boolean = true,
                  state: UIState<Any>, onButtonClick: () -> Unit, onSnackClick: (Boolean) -> Unit
) {
    val transition = updateTransition(
        targetState = state,
        label = "main transition",
    )
    val horizontalContentPadding by transition.animateDp(
        transitionSpec = {
            spring(
                stiffness = SpringStiffness,
            )
        },
        targetValueByState = { uiState ->
            when(uiState) {
                is UIState.Loading, is UIState.Error -> ZERO_DP
                else -> PADDING_24
            }
        },
        label = "button's content padding",
    )

    val shapeContent by transition.animateDp(
        transitionSpec = {
            spring(
                stiffness = SpringStiffness,
            )
        },
        targetValueByState = { uiState ->
            when(uiState) {
                is UIState.Loading, is UIState.Error -> CORNER_RADIUS_22
                else -> CORNER_RADIUS_4
            }
        },
        label = "button's content padding",
    )

    val widthContent by transition.animateFloat { uiState ->
        when(uiState) {
            is UIState.Loading, is UIState.Error -> 0.14f
            else -> 1.0f
        }
    }

    val loadingTransition = updateTransition(
        targetState = state is UIState.Loading,
        label = "main transition",
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        BaseButton(
            modifier = modifier
                .fillMaxWidth(fraction = widthContent)
                .animateContentSize(),
            shape = RoundedCornerShape(shapeContent),
            onClick = onButtonClick,
            enabled = enabled,
            innerPadding = PaddingValues(horizontal = horizontalContentPadding, vertical = PADDING_12)
        ) {
            Box(contentAlignment = Alignment.Center) {
                LoadingContent(
                    loadingStateTransition = loadingTransition
                )
                PrimaryContent(
                    text = text,
                    loadingStateTransition = loadingTransition
                )
            }
        }

        CustomSnackBar(modifier, state) { closeScreen ->
            onSnackClick.invoke(closeScreen)
        }
    }
}

private const val SpringStiffness = Spring.StiffnessMediumLow

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun LoadingContent(
    loadingStateTransition: Transition<Boolean>,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> loading },
        enter = fadeIn(),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(BUTTON_PROGRESS_HEIGHT),
            color = MaterialTheme.colors.onPrimary,
            strokeWidth = 2.0f.dp,
            strokeCap = StrokeCap.Round,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PrimaryContent(
    text: String,
    loadingStateTransition: Transition<Boolean>,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> !loading },
        enter = fadeIn() + expandHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                dampingRatio = Spring.DampingRatioMediumBouncy,
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            expandFrom = Alignment.CenterHorizontally,
        ),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ) + shrinkHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            shrinkTowards = Alignment.CenterHorizontally,
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}
