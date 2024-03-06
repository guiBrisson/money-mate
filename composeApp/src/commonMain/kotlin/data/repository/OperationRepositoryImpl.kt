package data.repository

import data.datasource.OperationDataSource
import domain.model.Operation
import domain.repository.OperationRepository


class OperationRepositoryImpl(
    private val localOperationDataSource: OperationDataSource,
) : OperationRepository {
    override suspend fun getById(id: Int): Result<Operation> {
        return localOperationDataSource.getById(id)
    }
}
