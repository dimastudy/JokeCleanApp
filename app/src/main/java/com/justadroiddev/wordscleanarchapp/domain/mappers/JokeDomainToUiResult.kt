package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi

interface JokeDomainToUiResult {

    fun map(data: List<JokeModelDomainAbstract>) : JokeUi
    fun map(errorType: ErrorType) : JokeUi

}