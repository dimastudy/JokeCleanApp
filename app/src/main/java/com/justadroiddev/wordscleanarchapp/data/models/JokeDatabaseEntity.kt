package com.justadroiddev.wordscleanarchapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeEntityToDataModel


@Entity(tableName = "favorite_jokes")
data class JokeDatabaseEntity constructor(
    val categories: String,
    val createdAt: String,
    val iconUrl: String,
    @PrimaryKey val id: String,
    val updatedAt: String,
    val url: String,
    val value: String
) {

    fun map(mapper: JokeEntityToDataModel) : JokeModelDataAbstract = mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)

}