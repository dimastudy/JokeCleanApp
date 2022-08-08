package com.justadroiddev.wordscleanarchapp.di

import android.content.Context
import com.justadroiddev.wordscleanarchapp.data.database.JokesDatabase
import com.justadroiddev.wordscleanarchapp.data.database.getDataJokesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideJokesDatabase(@ApplicationContext context: Context) : JokesDatabase = getDataJokesDatabase(context)


}