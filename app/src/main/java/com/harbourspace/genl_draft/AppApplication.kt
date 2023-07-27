package com.harbourspace.genl_draft

import android.app.Application
import com.harbourspace.genl_draft.repository.AppDatabase
import com.harbourspace.unsplash.repository.UnsplashRepository

class AppApplication: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

//    val repository by lazy { UnsplashRepository(database.unsplashDao()) }
}