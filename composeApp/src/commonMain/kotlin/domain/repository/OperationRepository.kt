package domain.repository

import domain.model.Category
import domain.model.Operation
import domain.model.OperationType
import domain.model.UIState
import kotlinx.coroutines.flow.Flow

interface OperationRepository {
    suspend fun getById(id: Int): Result<Operation>
    suspend fun getAll(): List<Operation>
    fun insertNew(amount: String, description: String, type: OperationType,
        category: Category, isPeriodic: Boolean
    ): Flow<UIState<Operation>>
}
