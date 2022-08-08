package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDatabaseEntity

class BaseJokeDataToEntityModel : JokeDataToEntityModel {
    override fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ): JokeDatabaseEntity = JokeDatabaseEntity(categories.first(), createdAt, iconUrl, id, updatedAt, url, value)
}