package com.justadroiddev.wordscleanarchapp.domain.usecases

import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiResult
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository,
    private val categoriesMapper: CategoriesDomainToUiResult
) {
    suspend operator fun invoke() = repository.getListCategories().map(categoriesMapper)
}