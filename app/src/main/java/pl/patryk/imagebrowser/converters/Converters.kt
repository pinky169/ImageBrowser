package pl.patryk.imagebrowser.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import pl.patryk.imagebrowser.model.ItemEntity
import pl.patryk.imagebrowser.model.SearchEntity
import java.lang.reflect.Type


class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<ItemEntity> {
        val listType: Type = object : TypeToken<List<ItemEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ItemEntity>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToSearchEntityList(value: String): List<SearchEntity> {
        val listType: Type = object : TypeToken<List<SearchEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListOfSearchEntities(list: List<SearchEntity>): String {
        return gson.toJson(list)
    }
}