package data.datasource.local

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.dsl.module


actual fun platformModule() = module {
    single {
        val driver = NativeSqliteDriver(MoneyMateDatabase.Schema, "MoneyMate.db")
        MoneyMateDatabaseWrapper(MoneyMateDatabase(driver))
    }
}