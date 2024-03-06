package domain.repository

import domain.model.Operation

// TODO: implement properly
interface OperationRepository {
    fun getById(id: Int): Result<Operation>
}
