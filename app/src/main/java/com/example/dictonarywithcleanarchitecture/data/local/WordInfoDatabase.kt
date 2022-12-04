package com.example.dictonarywithcleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictonarywithcleanarchitecture.data.local.entity.WordInfoEntity
import com.example.dictonarywithcleanarchitecture.data.local.util.Converters

@Database(entities = [WordInfoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao: WordInfoDao

}