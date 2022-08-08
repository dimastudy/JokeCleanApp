package com.justadroiddev.wordscleanarchapp.presentation.models

import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.presentation.mappers.JokeUiToDomainModel

sealed class JokeUi


data class JokeUiSuccess(
    private val data: List<JokeUiModel>
) : JokeUi() {
    fun showJoke() = data.first().showTextJoke()
    fun provideJokes() = data
}


data class JokeUiModel(
    private val categories: List<String>,
    private val createdAt: String,
    private val iconUrl: String,
    private val id: String,
    private val updatedAt: String,
    private val url: String,
    private val value: String
) {
    fun showTextJoke() = value
    fun provideIconUrl() = iconUrl
    fun provideCategory() = categories.first()
    fun provideDateCreation() = createdAt
    fun provideId() = id
    fun map(mapper: JokeUiToDomainModel) : JokeModelDomainAbstract = mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)
}

data class JokeUiFailure(
    private val error: String
) : JokeUi() {
    fun showError() = error
}


object JokeUiProgress : JokeUi()


