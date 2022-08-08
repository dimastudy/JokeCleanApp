package com.justadroiddev.wordscleanarchapp.domain.models

import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesUi


interface CategoriesModelDomainAbstract {
    fun map(mapper: CategoriesDomainToUiModel) : List<String>
}

data class CategoriesDomainModel(
    private val listCategories: List<String>
) : CategoriesModelDomainAbstract {
    override fun map(mapper: CategoriesDomainToUiModel): List<String> = mapper.map(listCategories)
}