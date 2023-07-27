package com.harbourspace.genl_draft.ui.data.converters

import androidx.room.TypeConverter
import com.harbourspace.genl_draft.ui.data.Urls
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UrlsConverter {

    @TypeConverter
    fun fromUrlsType(value: Urls?): String = Json.encodeToString(value)

    @TypeConverter
    fun toUrlsType(value: String): Urls? = Json.decodeFromString(value)
}