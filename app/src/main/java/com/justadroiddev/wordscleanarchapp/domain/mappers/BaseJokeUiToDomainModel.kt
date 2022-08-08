package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.domain.models.JokeDomainModel
import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.presentation.mappers.JokeUiToDomainModel

class BaseJokeUiToDomainModel : JokeUiToDomainModel {
    override fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ): JokeModelDomainAbstract = JokeDomainModel(categories, createdAt, iconUrl, id, updatedAt, url, value)
}