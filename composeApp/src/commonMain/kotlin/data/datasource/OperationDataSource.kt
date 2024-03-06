package data.datasource

import domain.model.Operation

interface OperationDataSource {
    suspend fun getById(id: Int): Result<Operation>
}
