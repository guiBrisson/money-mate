package domain.repository

import domain.model.Balance

interface BalanceRepository {
    suspend fun getUsersBalance(): Balance
    suspend fun updateUsersBalance(balance: Balance)
}
