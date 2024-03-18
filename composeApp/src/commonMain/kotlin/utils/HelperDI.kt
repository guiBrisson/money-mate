package utils

import data.datasource.local.localDatabasePlatformModule
import data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import presentation.di.viewModelModule


fun initKoin(appDeclaration: KoinAppDeclaration = { }) = startKoin {
    appDeclaration()
    modules(viewModelModule, dataModule, localDatabasePlatformModule())
}

// called by iOS
fun initKoin() = initKoin(appDeclaration = { })
