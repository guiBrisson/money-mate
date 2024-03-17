package data.datasource.local

import data.datasource.OperationDataSource
import domain.model.Category
import domain.model.Operation
import domain.model.OperationType
import kotlinx.datetime.LocalDateTime

class LocalOperationDataSource : OperationDataSource {
    override suspend fun getById(id: Int): Result<Operation> {
        //TODO remove this later, mocked for now
        return Result.success(
            Operation(
                id = -1,
                amount = 0.0,
                description = "Test",
                type = OperationType.INCOME.operation,
                category = Category.EDUCATION.name,
                date = LocalDateTime(2024, 3, 6, 1, 1 ,1, 1),
                isPeriodic = false,
            )
        )
    }
}
