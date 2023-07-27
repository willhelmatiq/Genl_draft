package com.harbourspace.genl_draft.ui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Links(
    val download: String,
    val html: String,
    val self: String
) : Parcelable