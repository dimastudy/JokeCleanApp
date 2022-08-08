package com.justadroiddev.wordscleanarchapp.data.repository

import com.justadroiddev.wordscleanarchapp.data.database.CacheDataSource
import com.justadroiddev.wordscleanarchapp.data.mappers.CategoriesDataToDomainResult
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeDataToDomainResult
import com.justadroiddev.wordscleanarchapp.data.models.CategoriesResultDataAbstract
import com.justadroiddev.wordscleanarchapp.data.network.CloudDataSource
import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToDataModel
import com.justadroiddev.wordscleanarchapp.domain.models.CategoriesResultDomainAbstract
import com.justadroiddev.wordscleanarchapp.domain.models.JokeDomainResult
import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.domain.models.JokeResultDomainAbstract
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val categoriesResultMapper: CategoriesDataToDomainResult,
    private val jokeResultMapper: JokeDataToDomainResult,
    private val jokeDomainToDataModel: JokeDomainToDataModel
) : Repository {
    override suspend fun getListCategories(): CategoriesResultDomainAbstract {
        val dataResult = cloudDataSource.getCategories()
        val domainResult = dataResult.map(categoriesResultMapper)
        return domainResult
    }

    override suspend fun getJokeByCategory(category: String): JokeResultDomainAbstract {
        val jokeData = cloudDataSource.getJoke(category)
        val jokeDomain = jokeData.map(jokeResultMapper)
        return jokeDomain
    }

    override suspend fun saveJoke(jokeDomain: JokeModelDomainAbstract) {
        cacheDataSource.saveJoke(jokeDomain.map(jokeDomainToDataModel))
    }

    override suspend fun removeJoke(jokeDomain: JokeModelDomainAbstract) {
        cacheDataSource.deleteJoke(jokeDomain.map(jokeDomainToDataModel))
    }

    override suspend fun fetchFavoriteJokes(): JokeResultDomainAbstract =
        cacheDataSource.fetchFavoriteJokes().map(jokeResultMapper)



}