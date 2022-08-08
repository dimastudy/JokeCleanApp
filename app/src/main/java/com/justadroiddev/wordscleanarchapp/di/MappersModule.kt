package com.justadroiddev.wordscleanarchapp.di

import com.justadroiddev.wordscleanarchapp.core.ResourceManager
import com.justadroiddev.wordscleanarchapp.data.mappers.*
import com.justadroiddev.wordscleanarchapp.domain.mappers.*
import com.justadroiddev.wordscleanarchapp.presentation.mappers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideJokeServerToDataModelMapper(): JokeServerToDataModel = BaseJokeServerToDataModel()

    @Provides
    fun provideCategoriesDataToDomainModelMapper(): CategoriesDataToDomainModel =
        BaseCategoriesDataToDomainModel()

    @Provides
    fun provideCategoriesDataToDomainResult(mapper: CategoriesDataToDomainModel): CategoriesDataToDomainResult =
        BaseCategoriesDataToDomainResult(mapper)

    @Provides
    fun provideCategoriesDomainToUiModel(): CategoriesDomainToUiModel =
        BaseCategoriesDomainToUiModel()

    @Provides
    fun provideCategoriesDomainToUiResult(
        resourceManager: ResourceManager,
        mapper: CategoriesDomainToUiModel
    ): CategoriesDomainToUiResult =
        BaseCategoriesDomainToUiResult(resourceManager, mapper)

    @Provides
    fun provideJokeDataToDomainModel(): JokeDataToDomainModel =
        BaseJokeDataToDomainModel()

    @Provides
    fun provideJokeDataToDomainResult(jokeMapper: JokeDataToDomainModel): JokeDataToDomainResult =
        BaseJokeDataToDomainResult(jokeMapper)

    @Provides
    fun provideJokeDomainToUiModel(): JokeDomainToUiModel =
        BaseJokeDomainToUiModel()

    @Provides
    fun provideJokeDomainToUiResult(
        resourceManager: ResourceManager,
        jokeMapper: JokeDomainToUiModel
    ): JokeDomainToUiResult =
        BaseJokeDomainToUiResult(resourceManager, jokeMapper)

    @Provides
    fun provideJokeEntityToDataModel(): JokeEntityToDataModel =
        BaseJokeEntityToDataModel()

    @Provides
    fun provideJokeUiToDomainModel(): JokeUiToDomainModel =
        BaseJokeUiToDomainModel()

    @Provides
    fun provideJokeDomainToDataModel(): JokeDomainToDataModel =
        BaseJokeDomainToDataModel()

    @Provides
    fun provideJokeDataToEntityModel(): JokeDataToEntityModel =
        BaseJokeDataToEntityModel()


}