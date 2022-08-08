package com.justadroiddev.wordscleanarchapp.data.network

import com.justadroiddev.wordscleanarchapp.data.models.JokeNetwork
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface JokesApi {

    companion object {
        private const val API_KEY = "28c082ff4bmsh6d182b2d9b7f6fcp13372bjsn21e253e44964"
        private const val HOST = "matchilling-chuck-norris-jokes-v1.p.rapidapi.com"
    }

    @Headers("X-RapidAPI-Key: $API_KEY", "X-RapidAPI-Host: $HOST")
    @GET("categories")
    suspend fun getCategories() : List<String>

    @Headers("X-RapidAPI-Key: $API_KEY", "X-RapidAPI-Host: $HOST")
    @GET("random")
    suspend fun getJoke(@Query("category") category: String) : JokeNetwork
}