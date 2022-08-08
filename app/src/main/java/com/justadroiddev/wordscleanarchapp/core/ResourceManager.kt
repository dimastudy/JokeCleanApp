package com.justadroiddev.wordscleanarchapp.core

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface ResourceManager {

    fun provideString(@StringRes idString: Int) : String

}

class BaseResourceManager @Inject constructor(
    private val context: Context
) : ResourceManager {
    override fun provideString(idString: Int): String =
        context.getString(idString)
}