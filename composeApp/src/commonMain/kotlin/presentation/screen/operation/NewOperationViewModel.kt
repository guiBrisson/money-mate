package presentation.screen.operation

import domain.model.Category
import domain.model.Operation
import domain.model.OperationType
import domain.model.UIState
import domain.repository.OperationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import presentation.base.BaseViewModel

class NewOperationViewModel(
    private val repository: OperationRepository
) : BaseViewModel() {

    private var _operation = MutableStateFlow<UIState<Operation>>(UIState.Empty)
    val operation: StateFlow<UIState<Operation>> = _operation

    fun refreshState() {
        refreshStateFlow(_operation)
    }

    fun saveOperation(
        amount: String,
        description: String,
        type: OperationType,
        category: Category,
        isPeriodic: Boolean
    ) {
        launch {
            _operation.emit(UIState.Loading)
            repository.insertNew(amount, description, type, category, isPeriodic).collect {
                _operation.emit(it)
            }
        }
    }
}
