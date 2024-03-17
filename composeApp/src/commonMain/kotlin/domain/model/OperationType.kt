package domain.model

import androidx.compose.ui.graphics.Color
import presentation.theme.expense
import presentation.theme.income

enum class OperationType(val operation: String, val color: Color) {
    EXPENSE("Expense", expense),
    INCOME("Income", income)
}