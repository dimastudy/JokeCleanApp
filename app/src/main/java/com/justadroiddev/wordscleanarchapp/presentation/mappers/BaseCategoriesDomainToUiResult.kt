package com.justadroiddev.wordscleanarchapp.presentation.mappers

import com.justadroiddev.wordscleanarchapp.R
import com.justadroiddev.wordscleanarchapp.core.ResourceManager
import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiModel
import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiResult
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesFailureUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesSuccessUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesUi
import javax.inject.Inject

class BaseCategoriesDomainToUiResult @Inject constructor(
    private val resourceManager: ResourceManager,
    private val mapper: CategoriesDomainToUiModel
) : CategoriesDomainToUiResult{
    override fun map(data: CategoriesModelDomainAbstract): CategoriesUi {
        return CategoriesSuccessUiModel(data.map(mapper))
    }

    override fun map(error: ErrorType): CategoriesUi {
        return when(error){
            ErrorType.NO_INTERNET -> CategoriesFailureUiModel(resourceManager.provideString(R.string.no_internet_message))
            ErrorType.CANT_READ -> CategoriesFailureUiModel(resourceManager.provideString(R.string.cant_read_message))
            ErrorType.EMPTY_LIST -> CategoriesFailureUiModel(resourceManager.provideString(R.string.empty_list_message))
            ErrorType.OTHER -> CategoriesFailureUiModel(resourceManager.provideString(R.string.other_error_message))
        }
    }
}