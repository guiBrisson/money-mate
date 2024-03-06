package domain.repository

import domain.model.Operation

// TODO: implement properly
interface OperationRepository {
    suspend fun getById(id: Int): Result<Operation>
}
