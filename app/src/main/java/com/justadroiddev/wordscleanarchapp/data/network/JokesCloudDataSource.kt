package com.justadroiddev.wordscleanarchapp.data.network

import android.util.Log
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeServerToDataModel
import com.justadroiddev.wordscleanarchapp.data.models.*
import java.lang.Exception
import javax.inject.Inject


class JokesCloudDataSource @Inject constructor(
    private val api: JokesApi,
    private val jokeNetworkMapper: JokeServerToDataModel
) : CloudDataSource {
    override suspend fun getCategories(): CategoriesResultDataAbstract {
        return try {
            val listCategories = api.getCategories()
            CategoriesDataResult.Success(CategoriesDataModel(listCategories))
        } catch (e: Exception){
            Log.e("CloudDataSource", "${e.message}")
            CategoriesDataResult.Failure(e)
        }
    }

    override suspend fun getJoke(category: String): JokeDataResult {
        return try {
            val jokeList = ArrayList<JokeModelDataAbstract>()
            while (jokeList.size < 5){
                val joke = api.getJoke(category)
                jokeList.add(joke.map(jokeNetworkMapper))
            }
            JokeDataResult.Success(jokeList)
        } catch (e: Exception){
            JokeDataResult.Failure(e)
        }
    }
}