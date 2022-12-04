package com.example.dictonarywithcleanarchitecture.domain.model

import com.example.dictonarywithcleanarchitecture.data.remote.dto.MeaningDto
import com.example.dictonarywithcleanarchitecture.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val origin: String,
    val word: String
)
