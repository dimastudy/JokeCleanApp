package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.data.mappers.CategoriesDataToDomainModel
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesDomainModel
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesModelDomainAbstract

class BaseCategoriesDataToDomainModel : CategoriesDataToDomainModel {
    override fun map(listCategories: List<String>): CategoriesModelDomainAbstract = CategoriesDomainModel(listCategories)
}