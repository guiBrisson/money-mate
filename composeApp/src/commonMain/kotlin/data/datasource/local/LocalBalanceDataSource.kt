package data.datasource.local

import co.touchlab.kermit.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import data.datasource.BalanceDataSource
import domain.model.Balance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class LocalBalanceDataSource : BalanceDataSource {
    private val settings: Settings = Settings()
    private val log = Logger.withTag(this::class.simpleName ?: "LocalBalanceDataSource")

    override suspend fun getUsersBalance(): Balance = withContext(Dispatchers.IO) {
            val usersBalance: String = settings.getString(BALANCE_KEY, BALANCE_DEFAULT_VALUE)
            Balance.fromString(usersBalance)
    }

    override suspend fun updateUsersBalance(balance: Balance) = withContext(Dispatchers.IO) {
        settings[BALANCE_KEY] = balance.toString()
        log.i("Update users balance: $balance")
    }

    companion object {
        const val BALANCE_KEY = "balance"
        const val BALANCE_DEFAULT_VALUE = ""
    }
}
