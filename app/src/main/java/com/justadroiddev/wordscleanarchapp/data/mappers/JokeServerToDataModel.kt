package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract

interface JokeServerToDataModel {

    fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ) : JokeModelDataAbstract

}