package com.justadroiddev.wordscleanarchapp.data.models

import com.google.gson.annotations.SerializedName
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToDomainModel
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToEntityModel
import com.justadroiddev.wordscleanarchapp.domain.models.JokeDomainModel
import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract

interface JokeModelDataAbstract {
    fun map(mapper: JokeDataToDomainModel): JokeModelDomainAbstract
    fun map(mapper: JokeDataToEntityModel): JokeDatabaseEntity
}


data class JokeDataModel(
    private val categories: List<String>,
    private val createdAt: String,
    private val iconUrl: String,
    private val id: String,
    private val updatedAt: String,
    private val url: String,
    private val value: String
) : JokeModelDataAbstract {

    override fun map(mapper: JokeDataToDomainModel): JokeDomainModel =
        mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)

    override fun map(mapper: JokeDataToEntityModel): JokeDatabaseEntity =
        mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)

}