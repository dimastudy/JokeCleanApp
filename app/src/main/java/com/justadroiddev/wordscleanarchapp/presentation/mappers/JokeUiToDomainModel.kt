package com.justadroiddev.wordscleanarchapp.presentation.mappers

import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract

interface JokeUiToDomainModel {

    fun map(
        categories: List<String>,
        createdAt: String,
        iconUrl: String,
        id: String,
        updatedAt: String,
        url: String,
        value: String
    ) : JokeModelDomainAbstract

}