package com.justadroiddev.wordscleanarchapp.domain.models

import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiResult
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesSuccessUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesUi


interface CategoriesResultDomainAbstract {
    fun map(mapper: CategoriesDomainToUiResult) : CategoriesUi
}


sealed class CategoriesDomainResult : CategoriesResultDomainAbstract{

    data class Success(private val data: CategoriesModelDomainAbstract) : CategoriesDomainResult() {
        override fun map(mapper: CategoriesDomainToUiResult): CategoriesUi = mapper.map(data)

    }

    data class Failure(private val errorType: ErrorType) : CategoriesDomainResult() {
        override fun map(mapper: CategoriesDomainToUiResult): CategoriesUi = mapper.map(errorType)
    }

}