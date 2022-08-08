package com.justadroiddev.wordscleanarchapp.data.database

import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToEntityModel
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeEntityToDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeDataResult
import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract
import com.justadroiddev.wordscleanarchapp.data.models.JokeResultDataAbstract

class JokesCacheDataSource(
    private val database: JokesDatabase,
    private val jokeEntityToData: JokeEntityToDataModel,
    private val jokeDataToEntity: JokeDataToEntityModel
) : CacheDataSource {

    override suspend fun fetchFavoriteJokes(): JokeResultDataAbstract {
        return try {
            val jokes = database.dao().fetchJokes().map { jokesEntity ->
                jokesEntity.map(jokeEntityToData)
            }
            if (jokes.isNullOrEmpty()){
                JokeDataResult.Failure(ArrayStoreException())
            } else {
                JokeDataResult.Success(jokes)
            }
        } catch (e: Exception) {
            JokeDataResult.Failure(e)
        }
    }

    override suspend fun saveJoke(joke: JokeModelDataAbstract) {
        database.dao().addJoke(joke.map(jokeDataToEntity))
    }

    override suspend fun deleteJoke(joke: JokeModelDataAbstract) {
        database.dao().removeJoke(joke.map(jokeDataToEntity))
    }
}