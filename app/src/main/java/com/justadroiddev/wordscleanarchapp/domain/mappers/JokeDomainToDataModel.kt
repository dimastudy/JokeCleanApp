package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract

interface JokeDomainToDataModel {

    fun map(categories: List<String>,
            createdAt: String,
            iconUrl: String,
            id: String,
            updatedAt: String,
            url: String,
            value: String) : JokeModelDataAbstract

}