package data.datasource.local

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.github.guibrisson.db.MoneyMateDatabase
import com.github.guibrisson.db.OperationTable
import kotlinx.datetime.LocalDateTime
import org.koin.core.module.Module

/**
 *  Should return a Koin Module with a single implementation of the DatabaseWrapper interface
 *  @see DatabaseWrapper
 *  @see Module
 */
expect fun localDatabasePlatformModule(): Module

interface DatabaseWrapper {
    val driver: SqlDriver

    fun instance(): MoneyMateDatabase = MoneyMateDatabase(driver, operationTableAdapter())

    private fun operationTableAdapter(): OperationTable.Adapter {
        return OperationTable.Adapter(
            dateAdapter = dateColumnAdapter(),
        )
    }

    private fun dateColumnAdapter(): ColumnAdapter<LocalDateTime, String> {
        return object : ColumnAdapter<LocalDateTime, String> {
            override fun decode(databaseValue: String): LocalDateTime {
                return LocalDateTime.parse(databaseValue)
            }

            override fun encode(value: LocalDateTime): String {
                return value.toString() //TODO this works fine but it needs a proper date parser
            }
        }
    }
}
