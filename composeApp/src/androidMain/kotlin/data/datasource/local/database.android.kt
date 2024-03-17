package data.datasource.local

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class AndroidDatabaseWrapper(context: Context) : DatabaseWrapper {
    private val driver = AndroidSqliteDriver(MoneyMateDatabase.Schema, context, "MoneyMate.db")
    override val instance: MoneyMateDatabase = MoneyMateDatabase(driver, operationTableAdapter())
}

actual fun localDatabasePlatformModule() = module {
    single {
        AndroidDatabaseWrapper(androidContext())
    }
}
