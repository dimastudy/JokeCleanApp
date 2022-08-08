package com.justadroiddev.wordscleanarchapp.domain.usecases

import javax.inject.Inject

data class JokesUseCases @Inject constructor(
    private val removeJokeUseCase: RemoveFavoriteJokeUseCase,
    private val saveJokeUseCase: SaveFavoriteJokeUseCase,
    private val getJokesUseCase: GetFavoriteJokesUseCase,
    private val getJokeUseCase: GetJokeUseCase
) {
    fun provideFavoriteJokesUseCase() = getJokesUseCase
    fun provideJokeUseCase() = getJokeUseCase
    fun provideSaveJokeUseCase() = saveJokeUseCase
    fun provideRemoveJokeUseCase() = removeJokeUseCase
}