package com.harbourspace.genl_draft.ui.data

import android.os.Parcelable
import androidx.room.TypeConverters
import com.harbourspace.genl_draft.ui.data.converters.LinksConverter
import com.harbourspace.genl_draft.ui.data.converters.ProfileImageConverter
import com.harbourspace.genl_draft.ui.data.converters.ResultConverter
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: String,
    @field:TypeConverters(LinksConverter::class)
    val links: Links,
    val name: String,
    val portfolio_url: String,
    @field:TypeConverters(ProfileImageConverter::class)
    val profile_image: ProfileImage,
    val twitter_username: String,
    val username: String
) : Parcelable