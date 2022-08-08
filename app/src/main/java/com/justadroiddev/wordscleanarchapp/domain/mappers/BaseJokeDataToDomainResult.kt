package com.justadroiddev.wordscleanarchapp.domain.mappers

import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToDomainModel
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToDomainResult
import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract
import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.models.JokeDomainResult
import com.justadroiddev.wordscleanarchapp.domain.models.JokeResultDomainAbstract
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class BaseJokeDataToDomainResult(
    private val mapper: JokeDataToDomainModel
) : JokeDataToDomainResult {
    override fun map(data: List<JokeModelDataAbstract>): JokeResultDomainAbstract {
        return JokeDomainResult.Success(data.map { jokeData->
            jokeData.map(mapper)
        })
    }

    override fun map(exception: Exception): JokeResultDomainAbstract {
        return when(exception){
            is HttpException -> JokeDomainResult.Failure(ErrorType.NO_INTERNET)
            is IOException -> JokeDomainResult.Failure(ErrorType.CANT_READ)
            is ArrayStoreException -> JokeDomainResult.Failure(ErrorType.EMPTY_LIST)
            else -> JokeDomainResult.Failure(ErrorType.OTHER)
        }
    }
}