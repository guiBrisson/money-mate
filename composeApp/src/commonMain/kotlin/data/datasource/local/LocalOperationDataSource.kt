package data.datasource.local

import com.github.guibrisson.db.MoneyMateDatabase
import data.datasource.OperationDataSource
import domain.model.Operation
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LocalOperationDataSource : KoinComponent, OperationDataSource {
    private val databaseWrapper: MoneyMateDatabaseWrapper by inject()
    private val database: MoneyMateDatabase? = databaseWrapper.instance

    override suspend fun getById(id: Int): Result<Operation> {
        //TODO remove this later, mocked for now
        return Result.success(
            Operation(
                id = -1,
                amount = 0.0,
                description = "Test",
                type = "",
                category = "",
                date = LocalDateTime(2024, 3, 6, 1, 1 ,1, 1),
                isPeriodic = false,
            )
        )
    }
}
