package com.justadroiddev.wordscleanarchapp.di

import android.app.Application
import android.content.Context
import com.justadroiddev.wordscleanarchapp.core.BaseResourceManager
import com.justadroiddev.wordscleanarchapp.core.JokesApp
import com.justadroiddev.wordscleanarchapp.core.ResourceManager
import com.justadroiddev.wordscleanarchapp.data.database.CacheDataSource
import com.justadroiddev.wordscleanarchapp.data.database.JokesCacheDataSource
import com.justadroiddev.wordscleanarchapp.data.database.JokesDatabase
import com.justadroiddev.wordscleanarchapp.data.mappers.*
import com.justadroiddev.wordscleanarchapp.data.network.CloudDataSource
import com.justadroiddev.wordscleanarchapp.data.network.JokesApi
import com.justadroiddev.wordscleanarchapp.data.network.JokesCloudDataSource
import com.justadroiddev.wordscleanarchapp.data.repository.JokesRepository
import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToDataModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object JokeModule {


    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager =
        BaseResourceManager(context)

    @Provides
    fun provideCloudDataSource(
        api: JokesApi,
        jokeServerToDataModel: JokeServerToDataModel
    ): CloudDataSource =
        JokesCloudDataSource(api, jokeServerToDataModel)

    @Provides
    fun provideCacheDataSource(
        database: JokesDatabase,
        jokeEntityToDataModel: JokeEntityToDataModel,
        jokeDataToEntityModel: JokeDataToEntityModel
    ): CacheDataSource =
        JokesCacheDataSource(database, jokeEntityToDataModel, jokeDataToEntityModel)


    @Provides
    @Singleton
    fun provideJokesRepository(
        cloudDataSource: CloudDataSource,
        cacheDataSource: CacheDataSource,
        categoriesResultMapper: CategoriesDataToDomainResult,
        jokeDataToDomainResult: JokeDataToDomainResult,
        jokeDomainToDataModel: JokeDomainToDataModel
    ): Repository =
        JokesRepository(cloudDataSource, cacheDataSource,categoriesResultMapper, jokeDataToDomainResult,jokeDomainToDataModel)


}