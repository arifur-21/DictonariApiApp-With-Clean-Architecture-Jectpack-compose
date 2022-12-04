package com.example.dictonarywithcleanarchitecture.data.remote

import com.example.dictonarywithcleanarchitecture.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictonaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>

    companion object{
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}