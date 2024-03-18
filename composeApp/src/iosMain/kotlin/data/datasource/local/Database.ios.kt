package data.datasource.local

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.dsl.module

class IOSDatabaseWrapper : DatabaseWrapper {
    override val driver = NativeSqliteDriver(MoneyMateDatabase.Schema, "MoneyMate.db")
}

actual fun localDatabasePlatformModule() = module {
    factory<DatabaseWrapper> { IOSDatabaseWrapper() }
}
