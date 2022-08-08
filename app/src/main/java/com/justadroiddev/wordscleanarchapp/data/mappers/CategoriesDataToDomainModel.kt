package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesModelDomainAbstract

interface CategoriesDataToDomainModel {

    fun map(listCategories: List<String>) : CategoriesModelDomainAbstract

}