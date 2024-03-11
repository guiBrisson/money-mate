package data.repository

import data.datasource.BalanceDataSource
import domain.model.Balance
import domain.repository.BalanceRepository

class BalanceRepositoryImpl(
    private val localBalanceDataSource: BalanceDataSource,
) : BalanceRepository {
    override suspend fun getUsersBalance(): Balance {
        return localBalanceDataSource.getUsersBalance()
    }

    override suspend fun updateUsersBalance(balance: Balance) {
        return localBalanceDataSource.updateUsersBalance(balance)
    }
}
