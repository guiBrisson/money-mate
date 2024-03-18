package domain.model

import kotlinx.datetime.LocalDateTime


data class Operation(
    val id: Long,
    val amount: Double,
    val description: String,
    val type: String,
    val category: String,
    val date: LocalDateTime,
    val isPeriodic: Boolean,
)
