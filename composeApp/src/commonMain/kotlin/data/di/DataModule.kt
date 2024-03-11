package data.di

import data.datasource.BalanceDataSource
import data.datasource.OperationDataSource
import data.datasource.local.LocalBalanceDataSource
import data.datasource.local.LocalOperationDataSource
import data.repository.BalanceRepositoryImpl
import data.repository.OperationRepositoryImpl
import domain.repository.BalanceRepository
import domain.repository.OperationRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    // Data source
    single<OperationDataSource>(named("local")){ LocalOperationDataSource() }
    single<BalanceDataSource>(named("local")) { LocalBalanceDataSource() }

    // Repository
    single<OperationRepository> { OperationRepositoryImpl(get(named("local"))) }
    single<BalanceRepository> { BalanceRepositoryImpl(get(named("local"))) }
}
