package com.justadroiddev.wordscleanarchapp.domain.mappers

interface CategoriesDomainToUiModel {
    fun map(
        listData: List<String>
    ) : List<String>
}