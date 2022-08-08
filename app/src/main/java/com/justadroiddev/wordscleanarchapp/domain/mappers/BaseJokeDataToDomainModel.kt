package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToDomainModel
import com.justadroiddev.wordscleanarchapp.domain.models.JokeDomainModel

class BaseJokeDataToDomainModel : JokeDataToDomainModel {
    override fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ): JokeDomainModel = JokeDomainModel(categories, createdAt, iconUrl, id, updatedAt, url, value)
}