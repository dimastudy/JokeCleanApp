package com.justadroiddev.wordscleanarchapp.presentation.mappers

import com.justadroiddev.wordscleanarchapp.R
import com.justadroiddev.wordscleanarchapp.core.ResourceManager
import com.justadroiddev.wordscleanarchapp.domain.ErrorType
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiModel
import com.justadroiddev.wordscleanarchapp.domain.mappers.JokeDomainToUiResult
import com.justadroiddev.wordscleanarchapp.domain.models.JokeModelDomainAbstract
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesFailureUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiFailure
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiSuccess
import javax.inject.Inject

class BaseJokeDomainToUiResult @Inject constructor(
    private val resourceManager: ResourceManager,
    private val jokeMapper: JokeDomainToUiModel
) : JokeDomainToUiResult {
    override fun map(data: List<JokeModelDomainAbstract>): JokeUi = JokeUiSuccess(data.map{ jokeDomain ->
        jokeDomain.map(jokeMapper)
    })

    override fun map(errorType: ErrorType): JokeUi {
        return when(errorType){
            ErrorType.NO_INTERNET -> JokeUiFailure(resourceManager.provideString(R.string.no_internet_message))
            ErrorType.CANT_READ -> JokeUiFailure(resourceManager.provideString(R.string.cant_read_message))
            ErrorType.EMPTY_LIST -> JokeUiFailure(resourceManager.provideString(R.string.empty_list_message))
            ErrorType.OTHER -> JokeUiFailure(resourceManager.provideString(R.string.other_error_message))
        }
    }
}