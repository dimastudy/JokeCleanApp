package com.justadroiddev.wordscleanarchapp.domain.usecases

import com.justadroiddev.wordscleanarchapp.domain.Repository
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiResult
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(
    private val repository: Repository,
    private val jokeMapper: JokeDomainToUiResult
) {
    suspend operator fun invoke(category: String) = repository.getJokeByCategory(category).map(jokeMapper)
}