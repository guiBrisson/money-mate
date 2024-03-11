package data.datasource

import domain.model.Balance

interface BalanceDataSource {
    suspend fun getUsersBalance(): Balance
    suspend fun updateUsersBalance(balance: Balance)
}
