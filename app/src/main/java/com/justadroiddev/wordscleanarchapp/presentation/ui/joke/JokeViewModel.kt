package com.justadroiddev.wordscleanarchapp.presentation.ui.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justadroiddev.wordscleanarchapp.domain.usecases.JokesUseCases
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiProgress
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiSuccess
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JokeViewModel @AssistedInject constructor(
    private val jokeUseCase: JokesUseCases,
    @Assisted private val category: String
) : ViewModel() {


    private val _jokeFlow = MutableStateFlow<JokeUi>(JokeUiProgress)
    val jokeFlow: StateFlow<JokeUi>
        get() = _jokeFlow


    init {
        fetchJokes()
    }

    fun fetchJokes() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val joke = jokeUseCase.provideJokeUseCase().invoke(category)
            _jokeFlow.value = joke
        }
    }

    fun refreshJokeList() {
        val list = ArrayList<JokeUiModel>()
        if (jokeFlow.value is JokeUiSuccess) {
            list.addAll((jokeFlow.value as JokeUiSuccess).provideJokes())
        }
        list.remove(list.first())
        if (list.isEmpty()) {
            _jokeFlow.value = JokeUiProgress
            fetchJokes()
        } else {
            _jokeFlow.value = JokeUiSuccess(list)
        }

    }

    fun saveJoke(joke: JokeUiModel) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            jokeUseCase.provideSaveJokeUseCase().invoke(joke)
        }
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            category: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(category) as T
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(category: String): JokeViewModel
    }


}