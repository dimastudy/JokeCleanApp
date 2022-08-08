package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract

class BaseJokeServerToDataModel : JokeServerToDataModel {
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