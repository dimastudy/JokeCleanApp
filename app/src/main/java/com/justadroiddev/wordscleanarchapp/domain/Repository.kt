package com.justadroiddev.wordscleanarchapp.domain

import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesResultDomainAbstract
import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.domain.models.JokeResultDomainAbstract

interface Repository {

    suspend fun getListCategories() : CategoriesResultDomainAbstract

    suspend fun getJokeByCategory(category: String) : JokeResultDomainAbstract

    suspend fun saveJoke(jokeDomain: JokeModelDomainAbstract)

    suspend fun removeJoke(jokeDomain: JokeModelDomainAbstract)

    suspend fun fetchFavoriteJokes() : JokeResultDomainAbstract


}