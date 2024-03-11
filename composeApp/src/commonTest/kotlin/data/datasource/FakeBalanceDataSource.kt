package data.datasource

import domain.model.Balance

class FakeBalanceDataSource: BalanceDataSource {
    override suspend fun getUsersBalance(): Balance {
        return Balance.fromString(usersBalance)
    }

    override suspend fun updateUsersBalance(balance: Balance) {
        usersBalance = balance.toString()
    }

    companion object {
        var usersBalance = ""
    }
}
