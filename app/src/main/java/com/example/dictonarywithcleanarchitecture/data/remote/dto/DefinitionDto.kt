package com.example.dictonarywithcleanarchitecture.data.remote.dto

import com.example.dictonarywithcleanarchitecture.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val synonyms: List<String>,
    val example: String?
){
    fun toDefinition(): Definition{
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}