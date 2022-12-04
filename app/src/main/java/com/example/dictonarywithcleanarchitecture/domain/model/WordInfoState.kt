package com.example.dictonarywithcleanarchitecture.domain.model

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
