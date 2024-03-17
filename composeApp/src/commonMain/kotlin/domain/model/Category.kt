package domain.model

import androidx.compose.ui.graphics.Color
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

enum class Category(val categoryName: String, val primaryColor: Color, val secondaryColor: Color) {
    HEALTH_WELLNESS("Health & Wellness", red600, red500),
    ENTERTAINMENT("Entertainment", orange600, orange500),
    SUBSCRIPTION("Subscription", amber500, amber600),
    INCOME("Income", lime600, lime500),
    DINING_OUT("Dining Out", emerald600, emerald500),
    EDUCATION("Education", cyan600, cyan500),
    GROCERIES("Croceries", blue600, blue500),
    TRAVEL("Travel", indigo500, indigo600),
    TRANSPORT("Transport", purple600, purple500),
    GIFTS_DONATIONS("Gifts & Donations", pink600, pink500),
    MISCELLANEOUS("Miscellaneous", rose600, rose500)
}