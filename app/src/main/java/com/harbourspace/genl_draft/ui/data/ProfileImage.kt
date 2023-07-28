package com.harbourspace.genl_draft.ui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ProfileImage(
    val large: String?,
    val medium: String?,
    val small: String?
) : Parcelable