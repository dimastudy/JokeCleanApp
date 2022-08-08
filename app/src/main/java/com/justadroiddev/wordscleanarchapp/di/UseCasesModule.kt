package com.justadroiddev.wordscleanarchapp.di

import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.domain.mappers.CategoriesDomainToUiResult
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiResult
import com.justadroiddev.wordscleanarchapp.domain.usecases.*
import com.justadroiddev.wordscleanarchapp.presentation.mappers.JokeUiToDomainModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideCategoryUseCase(
        repository: Repository,
        mapper: CategoriesDomainToUiResult
    ): GetCategoriesUseCase =
        GetCategoriesUseCase(repository, mapper)


    @Provides
    @Singleton
    fun provideGetJokeUseCase(
        repository: Repository,
        resultMapper: JokeDomainToUiResult
    ): GetJokeUseCase =
        GetJokeUseCase(repository, resultMapper)

    @Provides
    @Singleton
    fun provideSaveJokeUseCase(
        repository: Repository,
        mapper: JokeUiToDomainModel
    ): SaveFavoriteJokeUseCase =
        SaveFavoriteJokeUseCase(repository, mapper)

    @Provides
    @Singleton
    fun provideRemoveJokeUseCase(
        repository: Repository,
        mapper: JokeUiToDomainModel
    ): RemoveFavoriteJokeUseCase =
        RemoveFavoriteJokeUseCase(repository, mapper)

    @Provides
    @Singleton
    fun provideGetFavoriteJokesUseCase(
        repository: Repository,
        resultMapper: JokeDomainToUiResult
    ): GetFavoriteJokesUseCase =
        GetFavoriteJokesUseCase(repository, resultMapper)

    @Provides
    @Singleton
    fun provideJokesUseCases(
        removeFavoriteJokeUseCase: RemoveFavoriteJokeUseCase,
        saveFavoriteJokeUseCase: SaveFavoriteJokeUseCase,
        getFavoriteJokesUseCase: GetFavoriteJokesUseCase,
        getJokeUseCase: GetJokeUseCase
    ): JokesUseCases =
        JokesUseCases(
            removeFavoriteJokeUseCase,
            saveFavoriteJokeUseCase,
            getFavoriteJokesUseCase,
            getJokeUseCase
        )


}