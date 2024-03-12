package presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import moneymate.composeapp.generated.resources.*
import moneymate.composeapp.generated.resources.Res
import moneymate.composeapp.generated.resources.inter_light
import moneymate.composeapp.generated.resources.inter_medium
import moneymate.composeapp.generated.resources.inter_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class)
@Composable
fun interFontFamily() = FontFamily(
    Font(Res.font.inter_light, weight = FontWeight.Light),
    Font(Res.font.inter_regular),
    Font(Res.font.inter_medium, weight = FontWeight.Medium),
    Font(Res.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.inter_bold, weight = FontWeight.Bold)
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun oswaldFontFamily() = FontFamily(
    Font(Res.font.oswald_light, weight = FontWeight.Light),
    Font(Res.font.oswald_regular),
    Font(Res.font.oswald_medium, weight = FontWeight.Medium),
    Font(Res.font.oswald_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.oswald_bold, weight = FontWeight.Bold),
)

@Composable
fun getTypography(): Typography {
    return Typography(
        defaultFontFamily = interFontFamily(),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 16.sp,
        )
    )
}
