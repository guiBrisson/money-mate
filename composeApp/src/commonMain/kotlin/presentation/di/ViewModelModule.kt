package presentation.di

import org.koin.dsl.module
import presentation.screen.home.HomeViewModel

val viewModelModule = module {
    factory {
        HomeViewModel()
    }
}
