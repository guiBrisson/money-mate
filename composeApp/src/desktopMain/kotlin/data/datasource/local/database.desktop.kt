package data.datasource.local

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.dsl.module


actual fun platformModule() = module {
    single {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY) //TODO: Implement a proper driver
        MoneyMateDatabase.Schema.create(driver)
        MoneyMateDatabaseWrapper(MoneyMateDatabase(driver))
    }
}
