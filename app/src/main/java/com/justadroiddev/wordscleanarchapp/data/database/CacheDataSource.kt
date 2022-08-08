package com.justadroiddev.wordscleanarchapp.data.database

import com.justadroiddev.wordscleanarchapp.data.models.JokeModelDataAbstract
import com.justadroiddev.wordscleanarchapp.data.models.JokeResultDataAbstract

interface CacheDataSource {

    suspend fun fetchFavoriteJokes(): JokeResultDataAbstract

    suspend fun saveJoke(joke: JokeModelDataAbstract)

    suspend fun deleteJoke(joke: JokeModelDataAbstract)

}