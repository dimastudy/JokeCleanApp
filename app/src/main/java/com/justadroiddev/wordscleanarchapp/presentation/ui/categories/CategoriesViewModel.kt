package com.justadroiddev.wordscleanarchapp.presentation.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiResult
import com.justadroiddev.wordscleanarchapp.domain.usecases.GetCategoriesUseCase
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesProgress
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoriesFlow = MutableStateFlow<CategoriesUi>(CategoriesProgress)
    private val _categoriesLiveData = MutableLiveData<CategoriesUi>(CategoriesProgress)
    val categoriesFlow: StateFlow<CategoriesUi>
        get() = _categoriesFlow

    // todo: make own Dispatchers
    fun getCategories() = viewModelScope.launch() {
        withContext(Dispatchers.Default){
            val categories = categoriesUseCase.invoke()
            _categoriesFlow.value = categories
        }
    }

    init {
        getCategories()
    }

}