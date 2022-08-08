package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.CategoriesModelDataAbstract
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesResultDomainAbstract
import java.lang.Exception

interface CategoriesDataToDomainResult {
    fun map(data: CategoriesModelDataAbstract) : CategoriesResultDomainAbstract
    fun map(exception: Exception) : CategoriesResultDomainAbstract
}