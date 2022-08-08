package com.justadroiddev.wordscleanarchapp.data.models

import com.justadroiddev.wordscleanarchapp.data.mappers.CategoriesDataToDomainResult
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesResultDomainAbstract
import java.lang.Exception


interface CategoriesResultDataAbstract {

    fun map(mapper: CategoriesDataToDomainResult) : CategoriesResultDomainAbstract

}


sealed class CategoriesDataResult : CategoriesResultDataAbstract{

    data class Success(private val data: CategoriesDataModel) : CategoriesDataResult(){
        override fun map(mapper: CategoriesDataToDomainResult): CategoriesResultDomainAbstract = mapper.map(data)
    }

    data class Failure(private val exception: Exception) : CategoriesDataResult() {
        override fun map(mapper: CategoriesDataToDomainResult): CategoriesResultDomainAbstract = mapper.map(exception)
    }
}