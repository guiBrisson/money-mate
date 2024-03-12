package presentation.screen.balance

import domain.model.Balance
import domain.repository.BalanceRepository
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope


class BalanceViewModel(
    private val balanceRepository: BalanceRepository,
) : ViewModel() {

    fun updateUsersBalance(value: String, onBalanceUpdated: () -> Unit) {
        viewModelScope.launch {
            try {
                balanceRepository.updateUsersBalance(Balance.fromString(value))
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                onBalanceUpdated()
            }
        }
    }

}
