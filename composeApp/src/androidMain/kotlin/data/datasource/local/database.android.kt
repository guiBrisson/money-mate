package data.datasource.local

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class AndroidDatabaseWrapper(context: Context) : DatabaseWrapper {
    override val driver = AndroidSqliteDriver(MoneyMateDatabase.Schema, context, "MoneyMate.db")
}

actual fun localDatabasePlatformModule() = module {
    factory<DatabaseWrapper> { AndroidDatabaseWrapper(androidContext()) }
}
