package com.justadroiddev.wordscleanarchapp.presentation.ui.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justadroiddev.wordscleanarchapp.domain.usecases.JokesUseCases
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUi
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteJokesViewModel @Inject constructor(
    private val jokesUseCases: JokesUseCases
) : ViewModel() {

    private val _favoriteJokes = MutableStateFlow<JokeUi>(JokeUiProgress)
    val favoriteJokes: StateFlow<JokeUi>
        get() = _favoriteJokes



    init {
        fetchFavoriteJokes()
    }

    fun fetchFavoriteJokes() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            _favoriteJokes.value = jokesUseCases.provideFavoriteJokesUseCase().invoke()
        }
    }

    fun saveJokeAndUpdateList(jokeUi: JokeUiModel) = viewModelScope.launch {
        val savingJoke = async(Dispatchers.IO) {
            jokesUseCases.provideSaveJokeUseCase().invoke(jokeUi)
        }
        savingJoke.await()
        fetchFavoriteJokes()
    }

    fun removeJokeAndUpdateList(jokeUi: JokeUiModel) = viewModelScope.launch {
        val removingJoke = async(Dispatchers.IO) {
            jokesUseCases.provideRemoveJokeUseCase().invoke(jokeUi)
        }
        removingJoke.await()
        fetchFavoriteJokes()
    }

}