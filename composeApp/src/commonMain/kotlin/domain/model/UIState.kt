package domain.model

sealed class UIState<out T : Any> {
    data class Success<T : Any>(val result: T) : UIState<T>()
    data class Error(val error: Throwable) : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
}
