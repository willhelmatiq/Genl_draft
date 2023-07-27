package com.harbourspace.genl_draft.ui.data.converters

import androidx.room.TypeConverter
import com.harbourspace.genl_draft.ui.data.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ResultConverter {

    @TypeConverter
    fun fromResultType(value: List<Result>?): String = Json.encodeToString(value)

    @TypeConverter
    fun toResultType(value: String): List<Result>? = Json.decodeFromString(value)
}