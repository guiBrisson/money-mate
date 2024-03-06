package presentation.di

import org.koin.dsl.module
import presentation.screen.home.HomeViewModel
import presentation.screen.operation.NewOperationViewModel

val viewModelModule = module {
    factory { HomeViewModel() }
    factory { NewOperationViewModel() }
}
