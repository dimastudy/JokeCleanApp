package com.justadroiddev.wordscleanarchapp.data.mappers

import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract
import com.justadroiddev.wordscleanarchapp.domain.models.JokeResultDomainAbstract
import java.lang.Exception

interface JokeDataToDomainResult {

    fun map(data: List<JokeModelDataAbstract>) : JokeResultDomainAbstract
    fun map(exception: Exception) : JokeResultDomainAbstract

}