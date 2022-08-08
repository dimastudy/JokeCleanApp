package com.justadroiddev.wordscleanarchapp.presentation.mappers

import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiModel

class BaseCategoriesDomainToUiModel : CategoriesDomainToUiModel {
    override fun map(listData: List<String>): List<String> = listData
}