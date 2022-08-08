package com.justadroiddev.wordscleanarchapp.domain.usecases

import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.presentation.mappers.JokeUiToDomainModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel

class RemoveFavoriteJokeUseCase(
    private val repository: Repository,
    private val jokeMapper: JokeUiToDomainModel
) {
    suspend operator fun invoke(jokeUiModel: JokeUiModel) = repository.removeJoke(jokeUiModel.map(jokeMapper))
}