package data.di

import data.datasource.OperationDataSource
import data.datasource.local.LocalOperationDataSource
import data.repository.OperationRepositoryImpl
import domain.repository.OperationRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    // Data source
    single<OperationDataSource>(named("local")){ LocalOperationDataSource() }

    // Repository
    single<OperationRepository> { OperationRepositoryImpl(get(named("local"))) }
}
