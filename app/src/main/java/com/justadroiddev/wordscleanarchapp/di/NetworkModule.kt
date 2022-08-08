package com.justadroiddev.wordscleanarchapp.di

import com.justadroiddev.wordscleanarchapp.data.network.JokesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/"

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val logger = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : JokesApi = retrofit.create(JokesApi::class.java)



}