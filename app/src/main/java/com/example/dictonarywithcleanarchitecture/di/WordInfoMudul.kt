package com.example.dictonarywithcleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.example.dictonarywithcleanarchitecture.data.local.util.Converters
import com.example.dictonarywithcleanarchitecture.data.local.WordInfoDatabase
import com.example.dictonarywithcleanarchitecture.data.remote.DictonaryApi
import com.example.dictonarywithcleanarchitecture.data.repository.WordInfoRepositoryImpl
import com.example.dictonarywithcleanarchitecture.data.local.util.GsonParser
import com.example.dictonarywithcleanarchitecture.domain.repository.WordInfoRepository
import com.example.dictonarywithcleanarchitecture.domain.use_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoMudul {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo{
        return GetWordInfo(repository)
    }

    ////provide repository
    @Provides
    @Singleton
    fun privideWordInfoRepository(db: WordInfoDatabase, api: DictonaryApi): WordInfoRepository{
        return WordInfoRepositoryImpl(api, db.dao)

    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase
    {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    @Provides
    @Singleton
    fun privideDictionaryApi(): DictonaryApi{
        return Retrofit.Builder()
            .baseUrl(DictonaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictonaryApi::class.java)

    }
}