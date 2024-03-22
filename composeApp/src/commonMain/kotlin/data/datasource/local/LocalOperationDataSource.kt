package data.datasource.local

import co.touchlab.kermit.Logger
import com.github.guibrisson.db.MoneyMateDatabase
import com.github.guibrisson.db.OperationTable
import data.datasource.OperationDataSource
import domain.model.Category
import domain.model.Operation
import domain.model.OperationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.extensions.currentDate
import utils.extensions.toLong
import kotlin.random.Random

class LocalOperationDataSource: KoinComponent, OperationDataSource {
    private val databaseWrapper: DatabaseWrapper by inject()
    private val database: MoneyMateDatabase = databaseWrapper.instance()
    private val log = Logger.withTag(this::class.simpleName ?: "LocalOperationDataSource")

    override suspend fun getById(id: Int): Result<Operation> {
        //TODO remove this later, mocked for now
        return Result.success(
            Operation(
                id = -1,
                amount = 0.0,
                description = "Test",
                type = OperationType.INCOME,
                category = Category.EDUCATION,
                date = LocalDateTime(2024, 3, 6, 1, 1 ,1, 1),
                isPeriodic = false,
            )
        )
    }

    override suspend fun getAll(): List<Operation> = withContext(Dispatchers.IO) {
        return@withContext database.operationQueries.allOperations().executeAsList().toOperationsList()
    }

    override suspend fun insert(operation: Operation): Flow<Operation> = flow {
        database.operationQueries.insertOperation(operation.toOperationTable())
        emit(operation)
    }

    private fun Operation.toOperationTable(): OperationTable =
        OperationTable(
            id = Random.nextLong(),
            amount = this.amount,
            description = this.description,
            type = this.type.operation,
            category = this.category.categoryName,
            date = Clock.System.now().currentDate(),
            isPeriodic = this.isPeriodic.toLong()
        )

    private fun List<OperationTable>.toOperationsList(): List<Operation> =
        map { table ->
            Operation(
                id = table.id,
                amount = table.amount,
                description = table.description,
                type = OperationType.valueOf(table.type.uppercase()),
                category = Category.valueOf(table.category.uppercase()),
                date = table.date,
                isPeriodic = table.isPeriodic != 0L
            )
        }
}
