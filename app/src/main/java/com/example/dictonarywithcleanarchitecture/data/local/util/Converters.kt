package com.example.dictonarywithcleanarchitecture.data.local.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictonarywithcleanarchitecture.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning>{
        return  jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        )?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meaings: List<Meaning>): String{
        return jsonParser.toJson(
            meaings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}