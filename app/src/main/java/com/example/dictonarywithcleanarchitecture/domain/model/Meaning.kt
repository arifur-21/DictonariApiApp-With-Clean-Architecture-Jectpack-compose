package com.example.dictonarywithcleanarchitecture.domain.model

import com.example.dictonarywithcleanarchitecture.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String,
)
