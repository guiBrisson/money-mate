package data.datasource.local

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.dsl.module

class DesktopDatabaseWrapper : DatabaseWrapper {
    private val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY) //TODO: Implement a proper driver
    override val instance: MoneyMateDatabase = MoneyMateDatabase(driver, operationTableAdapter())
}

actual fun localDatabasePlatformModule() = module {
    single {
        DesktopDatabaseWrapper()
    }
}
