package data.datasource.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import data.datasource.BalanceDataSource
import domain.model.Balance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class LocalBalanceDataSource : BalanceDataSource {
    private val settings: Settings = Settings()

    override suspend fun getUsersBalance(): Balance = withContext(Dispatchers.IO) {
            val usersBalance: String = settings.getString(BALANCE_KEY, BALANCE_DEFAULT_VALUE)
            Balance.fromString(usersBalance)
    }

    override suspend fun updateUsersBalance(balance: Balance) = withContext(Dispatchers.IO) {
        settings[BALANCE_KEY] = balance.toString()
    }

    companion object {
        const val BALANCE_KEY = "balance"
        const val BALANCE_DEFAULT_VALUE = ""
    }
}
