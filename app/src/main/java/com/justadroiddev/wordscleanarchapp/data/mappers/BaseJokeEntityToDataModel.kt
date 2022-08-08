package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract

class BaseJokeEntityToDataModel : JokeEntityToDataModel {
    override fun map(
        categories: String,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ): JokeModelDataAbstract = JokeDataModel(listOf(categories), createdAt, iconUrl, id, updatedAt, url, value)


}