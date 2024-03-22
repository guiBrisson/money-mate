package domain.model

import androidx.compose.ui.graphics.Color
import moneymate.composeapp.generated.resources.Res
import moneymate.composeapp.generated.resources.ic_dining_out
import moneymate.composeapp.generated.resources.ic_education
import moneymate.composeapp.generated.resources.ic_entertainment
import moneymate.composeapp.generated.resources.ic_gifts
import moneymate.composeapp.generated.resources.ic_groceries
import moneymate.composeapp.generated.resources.ic_health
import moneymate.composeapp.generated.resources.ic_income
import moneymate.composeapp.generated.resources.ic_miscellaneous
import moneymate.composeapp.generated.resources.ic_subscription
import moneymate.composeapp.generated.resources.ic_transport
import moneymate.composeapp.generated.resources.ic_travel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.theme.amber500
import presentation.theme.amber600
import presentation.theme.blue500
import presentation.theme.blue600
import presentation.theme.cyan500
import presentation.theme.cyan600
import presentation.theme.emerald500
import presentation.theme.emerald600
import presentation.theme.indigo500
import presentation.theme.indigo600
import presentation.theme.lime500
import presentation.theme.lime600
import presentation.theme.orange500
import presentation.theme.orange600
import presentation.theme.pink500
import presentation.theme.pink600
import presentation.theme.purple500
import presentation.theme.purple600
import presentation.theme.red500
import presentation.theme.red600
import presentation.theme.rose500
import presentation.theme.rose600

@OptIn(ExperimentalResourceApi::class)
enum class Category(val categoryName: String, val primaryColor: Color,
                    val secondaryColor: Color, val icon: DrawableResource) {
    HEALTH_WELLNESS("Health & Wellness", red600, red500, Res.drawable.ic_health),
    ENTERTAINMENT("Entertainment", orange600, orange500, Res.drawable.ic_entertainment),
    SUBSCRIPTION("Subscription", amber500, amber600, Res.drawable.ic_subscription),
    INCOME("Income", lime600, lime500, Res.drawable.ic_income),
    DINING_OUT("Dining Out", emerald600, emerald500, Res.drawable.ic_dining_out),
    EDUCATION("Education", cyan600, cyan500, Res.drawable.ic_education),
    GROCERIES("Groceries", blue600, blue500, Res.drawable.ic_groceries),
    TRAVEL("Travel", indigo500, indigo600, Res.drawable.ic_travel),
    TRANSPORT("Transport", purple600, purple500, Res.drawable.ic_transport),
    GIFTS_DONATIONS("Gifts & Donations", pink600, pink500, Res.drawable.ic_gifts),
    MISCELLANEOUS("Miscellaneous", rose600, rose500, Res.drawable.ic_miscellaneous);

    companion object {
        fun all(): Array<Category> = enumValues<Category>()
        fun default() = ENTERTAINMENT
    }
}