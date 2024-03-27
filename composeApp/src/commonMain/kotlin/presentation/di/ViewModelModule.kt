package presentation.di

import org.koin.dsl.module
import presentation.screen.balance.BalanceViewModel
import presentation.screen.category.CategoryViewModel
import presentation.screen.home.HomeViewModel
import presentation.screen.operation.NewOperationViewModel

val viewModelModule = module {
    factory { HomeViewModel() }
    factory { NewOperationViewModel() }
    factory { BalanceViewModel(get()) }
    factory { CategoryViewModel() }
}
