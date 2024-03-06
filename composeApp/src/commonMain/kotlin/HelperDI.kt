import data.di.dataModule
import org.koin.core.context.startKoin
import presentation.di.viewModelModule


fun initKoin() {
    startKoin {
        modules(viewModelModule, dataModule)
    }
}