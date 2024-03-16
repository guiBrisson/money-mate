package data.datasource.local

import com.github.guibrisson.db.MoneyMateDatabase
import org.koin.core.module.Module


expect fun platformModule(): Module

class MoneyMateDatabaseWrapper(val instance: MoneyMateDatabase?)
