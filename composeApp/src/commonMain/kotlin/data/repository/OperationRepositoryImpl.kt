package data.repository

import data.datasource.OperationDataSource
import domain.model.Category
import domain.model.Operation
import domain.model.OperationType
import domain.model.UIState
import domain.repository.OperationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


class OperationRepositoryImpl(
    private val localOperationDataSource: OperationDataSource,
) : OperationRepository {
    override suspend fun getById(id: Int): Result<Operation> {
        return localOperationDataSource.getById(id)
    }

    override suspend fun getAll(): List<Operation> =
        localOperationDataSource.getAll()

    override fun insertNew(amount: String, description: String, type: OperationType,
                                   category: Category, isPeriodic: Boolean
    ): Flow<UIState<Operation>> = flow {
        val newOperation = Operation(
            amount = amount.toDouble(),
            description = description,
            type = type,
            category = category,
            isPeriodic = isPeriodic
        )
        localOperationDataSource
            .insert(newOperation)
            .catch {
                emit(UIState.Error(it))
            }
            .collect {
                emit(UIState.Success(it))
            }
    }
}
