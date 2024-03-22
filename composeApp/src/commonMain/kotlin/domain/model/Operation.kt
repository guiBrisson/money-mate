package domain.model

import kotlinx.datetime.LocalDateTime

data class Operation (
    val id: Long? = null,
    val amount: Double = 0.0,
    val description: String = "",
    val type: OperationType = OperationType.default(),
    val category: Category = Category.default(),
    val date: LocalDateTime? = null,
    val isPeriodic: Boolean = false
)
