package com.justadroiddev.wordscleanarchapp.presentation.models

sealed class CategoriesUi

class CategoriesSuccessUiModel(
    private val listCategories: List<String>
) : CategoriesUi() {
    fun showCategories() = listCategories
}

object CategoriesProgress : CategoriesUi()

class CategoriesFailureUiModel(
    private val error: String
) : CategoriesUi() {
    fun showError() = error
}