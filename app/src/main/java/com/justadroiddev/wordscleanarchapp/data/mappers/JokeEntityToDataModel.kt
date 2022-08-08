package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract

interface JokeEntityToDataModel {

    fun map(
        categories: String,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ) : JokeModelDataAbstract

}