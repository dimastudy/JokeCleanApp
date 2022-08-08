package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDatabaseEntity

interface JokeDataToEntityModel {

    fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ) : JokeDatabaseEntity

}