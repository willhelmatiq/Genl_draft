package com.harbourspace.genl_draft.ui.data

import android.os.Parcelable
import androidx.room.TypeConverters
import com.harbourspace.genl_draft.ui.data.converters.LinksConverter
import com.harbourspace.genl_draft.ui.data.converters.UrlsConverter
import com.harbourspace.genl_draft.ui.data.converters.UserConverter
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Result(
    val blur_hash: String?,
    val color: String?,
    val created_at: String?,
    val description: String?,
    val height: Int?,
    val id: String?,
    val liked_by_user: Boolean?,
    val likes: Int?,
    @field:TypeConverters(LinksConverter::class)
    val links: Links?,
    @field:TypeConverters(UrlsConverter::class)
    val urls: Urls?,
    @field:TypeConverters(UserConverter::class)
    val user: User?,
    val width: Int?
) : Parcelable