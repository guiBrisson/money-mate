package data.datasource

import domain.model.Operation
import kotlinx.coroutines.flow.Flow

interface OperationDataSource {
    suspend fun getById(id: Int): Result<Operation>
    suspend fun getAll(): List<Operation>
    suspend fun insert(operation: Operation): Flow<Operation>
}
