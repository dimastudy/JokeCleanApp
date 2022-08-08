package com.justadroiddev.wordscleanarchapp.domain.usecases

import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.presentation.mappers.JokeUiToDomainModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel
import javax.inject.Inject

class SaveFavoriteJokeUseCase @Inject constructor(
    private val repository: Repository,
    private val jokeMapper: JokeUiToDomainModel
) {
    suspend operator fun invoke(jokeUi: JokeUiModel) = repository.saveJoke(jokeUi.map(jokeMapper))
}