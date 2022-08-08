package com.justadroiddev.wordscleanarchapp.domain.models

import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToDataModel
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel


interface JokeModelDomainAbstract{
    fun map(mapper: JokeDomainToUiModel) : JokeUiModel
    fun map(mapper: JokeDomainToDataModel) : JokeModelDataAbstract
}

data class JokeDomainModel(
    private val categories: List<String>,
    private val createdAt: String,
    private val iconUrl: String,
    private val id: String,
    private val updatedAt: String,
    private val url: String,
    private val value: String
) : JokeModelDomainAbstract{
    override fun map(mapper: JokeDomainToUiModel): JokeUiModel = mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)
    override fun map(mapper: JokeDomainToDataModel): JokeModelDataAbstract = mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)
}