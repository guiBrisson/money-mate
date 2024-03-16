import data.datasource.local.platformModule
import data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import presentation.di.viewModelModule


fun initKoin(appDeclaration: KoinAppDeclaration = { }) = startKoin {
    appDeclaration()
    modules(viewModelModule, dataModule, platformModule())
}
