package data.datasource.local

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.dsl.module

class IOSDatabaseWrapper : DatabaseWrapper {
    private val driver = NativeSqliteDriver(MoneyMateDatabase.Schema, "MoneyMate.db")
    override val instance: MoneyMateDatabase = MoneyMateDatabase(driver, operationTableAdapter())
}

actual fun localDatabasePlatformModule() = module {
    single {
        IOSDatabaseWrapper()
    }
}
