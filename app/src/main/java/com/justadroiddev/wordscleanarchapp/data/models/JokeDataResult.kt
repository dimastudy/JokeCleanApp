package com.justadroiddev.wordscleanarchapp.data.models

import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToDomainResult
import com.justadroiddev.wordscleanarchapp.domain.models.JokeDomainResult
import com.justadroiddev.wordscleanarchapp.domain.models.JokeResultDomainAbstract
import java.lang.Exception


interface JokeResultDataAbstract {

    fun map(mapper: JokeDataToDomainResult) : JokeResultDomainAbstract

}

sealed class JokeDataResult : JokeResultDataAbstract{

    data class Success(private val data: List<JokeModelDataAbstract>) : JokeDataResult() {
        override fun map(mapper: JokeDataToDomainResult): JokeResultDomainAbstract = mapper.map(data)
    }

    data class Failure(private val exception: Exception) : JokeDataResult() {
        override fun map(mapper: JokeDataToDomainResult): JokeResultDomainAbstract = mapper.map(exception)
    }

}