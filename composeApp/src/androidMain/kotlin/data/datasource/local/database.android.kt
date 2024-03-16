package data.datasource.local

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


actual fun platformModule() = module {
    single {
        val driver =
            AndroidSqliteDriver(MoneyMateDatabase.Schema, androidContext(), "MoneyMate.db")
        MoneyMateDatabaseWrapper(MoneyMateDatabase(driver))
    }
}
