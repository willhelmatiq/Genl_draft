package com.harbourspace.unsplash.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harbourspace.genl_draft.ui.data.UnsplashPhoto


@Dao
interface UnsplashDAO {

    @Query("SELECT * FROM UnsplashPhoto")
    fun getAllImages(): LiveData<List<UnsplashPhoto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImage(image: UnsplashPhoto)
}