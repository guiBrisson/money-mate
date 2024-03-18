package data.datasource.local

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.dsl.module

class DesktopDatabaseWrapper : DatabaseWrapper {
    private val jdbcURL ="jdbc:sqlite:./build/MoneyMate.db"
    override val driver = JdbcSqliteDriver(jdbcURL, schema = MoneyMateDatabase.Schema)
}

actual fun localDatabasePlatformModule() = module {
    factory<DatabaseWrapper> { DesktopDatabaseWrapper() }
}
