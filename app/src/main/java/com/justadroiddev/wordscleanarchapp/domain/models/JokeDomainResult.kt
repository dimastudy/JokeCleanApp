package com.justadroiddev.wordscleanarchapp.domain.models

import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiResult
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi


interface JokeResultDomainAbstract{

    fun map(mapper: JokeDomainToUiResult) : JokeUi

}

sealed class JokeDomainResult : JokeResultDomainAbstract{

    data class Success(private val data: List<JokeModelDomainAbstract>) : JokeDomainResult(){
        override fun map(mapper: JokeDomainToUiResult): JokeUi = mapper.map(data)

    }

    data class Failure(private val errorType: ErrorType) : JokeDomainResult() {
        override fun map(mapper: JokeDomainToUiResult): JokeUi = mapper.map(errorType)

    }

}