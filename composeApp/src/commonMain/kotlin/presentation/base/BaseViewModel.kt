package presentation.base

import domain.model.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

abstract class BaseViewModel: ViewModel() {

    protected fun <T : Any>refreshStateFlow(stateFlow: MutableStateFlow<UIState<T>>) {
        launch { stateFlow.emit(UIState.Empty) }
    }

    protected fun launch(
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch {
        block()
    }
}