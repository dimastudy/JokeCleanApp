package com.justadroiddev.wordscleanarchapp.data.network

import com.justadroiddev.wordscleanarchapp.data.models.CategoriesResultDataAbstract
import com.justadroiddev.wordscleanarchapp.data.models.JokeDataModel
import com.justadroiddev.wordscleanarchapp.data.models.JokeDataResult

interface CloudDataSource {

    suspend fun getCategories() : CategoriesResultDataAbstract

    suspend fun getJoke(category: String) : JokeDataResult

}