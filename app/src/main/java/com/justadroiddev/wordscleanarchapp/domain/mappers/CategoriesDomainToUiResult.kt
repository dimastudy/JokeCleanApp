package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesUi

interface CategoriesDomainToUiResult {

    fun map(data : CategoriesModelDomainAbstract) : CategoriesUi
    fun map(error: ErrorType) : CategoriesUi

}