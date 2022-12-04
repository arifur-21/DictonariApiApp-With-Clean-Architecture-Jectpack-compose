package com.example.dictonarywithcleanarchitecture.domain.repository

import com.example.dictonarywithcleanarchitecture.core.util.Resource
import com.example.dictonarywithcleanarchitecture.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}