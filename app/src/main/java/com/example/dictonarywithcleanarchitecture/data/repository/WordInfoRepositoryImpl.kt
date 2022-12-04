package com.example.dictonarywithcleanarchitecture.data.repository

import com.example.dictonarywithcleanarchitecture.core.util.Resource
import com.example.dictonarywithcleanarchitecture.data.local.WordInfoDao
import com.example.dictonarywithcleanarchitecture.data.remote.DictonaryApi
import com.example.dictonarywithcleanarchitecture.domain.model.WordInfo
import com.example.dictonarywithcleanarchitecture.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictonaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    /// room database dao
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWoredInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            ////retrofit api
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        }catch (e: HttpException){
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = wordInfos
            ))

        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWoredInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}