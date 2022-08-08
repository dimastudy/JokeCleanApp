package com.justadroiddev.wordscleanarchapp.data.models


import com.google.gson.annotations.SerializedName
import com.justadroiddev.wordscleanarchapp.data.mappers.JokeServerToDataModel

data class JokeNetwork(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("value")
    val value: String
) {

    fun map(mapper: JokeServerToDataModel) : JokeModelDataAbstract = mapper.map(categories, createdAt, iconUrl, id, updatedAt, url, value)

}