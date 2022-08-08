package com.justadroiddev.wordscleanarchapp.presentation.mappers

import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiSuccess

class BaseJokeDomainToUiModel : JokeDomainToUiModel {
    override fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ): JokeUiModel = JokeUiModel(categories, createdAt, iconUrl, id, updatedAt, url, value)
}