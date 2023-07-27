package com.harbourspace.genl_draft.ui.data.cb

import com.harbourspace.genl_draft.ui.data.UnsplashPhoto

interface UnsplashResult {

    fun onDataFetchedSuccess(image: UnsplashPhoto)

    fun onDataFetchedFailed()
}