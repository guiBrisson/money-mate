package presentation.screen.category

import domain.model.Category
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import moe.tlaster.precompose.viewmodel.ViewModel

class CategoryViewModel() : ViewModel() {

    private val _filterText = MutableStateFlow("")
    val filterText: StateFlow<String> = _filterText

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredItems: Flow<Array<Category>> = _filterText.flatMapLatest { filter ->
        flowOf(Category.filterCategory(filter))
    }

    fun onFilterTextChanged(newFilter: String) {
        _filterText.value = newFilter
    }
}
