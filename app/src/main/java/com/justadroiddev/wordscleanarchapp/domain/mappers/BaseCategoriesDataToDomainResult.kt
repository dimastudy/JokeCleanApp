package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.data.mappers.CategoriesDataToDomainModel
import com.justadroiddev.wordscleanarchapp.data.mappers.CategoriesDataToDomainResult
import com.justadroiddev.wordscleanarchapp.data.models.CategoriesModelDataAbstract
import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesDomainResult
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesResultDomainAbstract
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class BaseCategoriesDataToDomainResult @Inject constructor(
    private val mapper: CategoriesDataToDomainModel
) : CategoriesDataToDomainResult {
    override fun map(data: CategoriesModelDataAbstract): CategoriesResultDomainAbstract {
        return CategoriesDomainResult.Success(data.map(mapper))
    }

    override fun map(exception: Exception): CategoriesResultDomainAbstract {
        return when(exception){
            is HttpException -> CategoriesDomainResult.Failure(ErrorType.NO_INTERNET)
            is IOException -> CategoriesDomainResult.Failure(ErrorType.CANT_READ)
            else -> CategoriesDomainResult.Failure(ErrorType.OTHER)
        }
    }
}