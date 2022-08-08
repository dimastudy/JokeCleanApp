package com.justadroiddev.wordscleanarchapp.data.models


import com.justadroiddev.wordscleanarchapp.data.mappers.CategoriesDataToDomainModel
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesModelDomainAbstract


interface CategoriesModelDataAbstract {

    fun map(mapper: CategoriesDataToDomainModel) : CategoriesModelDomainAbstract

}

data class CategoriesDataModel (
    private val listCategories: List<String>
) : CategoriesModelDataAbstract {
    override fun map(mapper: CategoriesDataToDomainModel) = mapper.map(listCategories)
}