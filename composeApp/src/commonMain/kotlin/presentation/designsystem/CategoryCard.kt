package presentation.designsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import domain.model.Category
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.theme.CORNER_RADIUS_4
import presentation.theme.EXTRA_SMALL_PADDING
import presentation.theme.MEDIUM_INPUT_HEIGHT
import presentation.theme.MIN_INPUT_HEIGHT
import presentation.theme.MIN_INPUT_WIDTH
import presentation.theme.MIN_SMALL_PADDING

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CategoryCard(
    value: Category,

    ) {
    Card(
        modifier = Modifier
            .padding(vertical = MIN_SMALL_PADDING)
            .fillMaxWidth()
            .defaultMinSize(minHeight = MEDIUM_INPUT_HEIGHT),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(corner = CornerSize(CORNER_RADIUS_4)),

        ) {
        Row(
            modifier = Modifier.padding(EXTRA_SMALL_PADDING),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                modifier = Modifier
                    .width(MIN_INPUT_WIDTH)
                    .height(MIN_INPUT_HEIGHT)
                    .background(
                        value.secondaryColor.copy(alpha = 0.2f),
                        RoundedCornerShape(CORNER_RADIUS_4)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(value.icon),
                    contentDescription = value.categoryName,
                    colorFilter = ColorFilter.tint(value.primaryColor)
                )
            }

            Text(
                modifier = Modifier
                    .padding(EXTRA_SMALL_PADDING),
                text = value.categoryName,
            )
        }
    }
}