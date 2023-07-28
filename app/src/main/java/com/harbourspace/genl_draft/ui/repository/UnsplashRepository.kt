package com.harbourspace.unsplash.repository

import androidx.lifecycle.LiveData
import com.harbourspace.genl_draft.ui.repository.AppDatabase
import com.harbourspace.genl_draft.ui.data.UnsplashPhoto

class UnsplashRepository(private val dao: UnsplashDAO) {

    val items: LiveData<List<UnsplashPhoto>> = dao.getAllImages()

    fun insert(item: UnsplashPhoto) {
        AppDatabase.databaseWriteExecutor.execute {
            dao.insertImage(item)
        }
    }
}