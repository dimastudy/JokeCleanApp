package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToDataModel

class BaseJokeDomainToDataModel : JokeDomainToDataModel {
    override fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ): JokeModelDataAbstract = JokeDataModel(categories, createdAt, iconUrl, id, updatedAt, url, value)
}