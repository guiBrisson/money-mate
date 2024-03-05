package presentation.di

import org.koin.core.context.startKoin


fun initKoin() {
    startKoin {
        modules(viewModelModule)
    }
}