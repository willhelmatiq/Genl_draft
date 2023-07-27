package com.harbourspace.genl_draft.ui.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.harbourspace.genl_draft.ui.data.converters.ResultConverter
import kotlinx.parcelize.Parcelize

@Entity("UnsplashPhoto")
@Parcelize
data class UnsplashPhoto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @field:TypeConverters(ResultConverter::class)
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
) : Parcelable