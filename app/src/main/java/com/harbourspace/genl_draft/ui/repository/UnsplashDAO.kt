package com.harbourspace.unsplash.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.harbourspace.genl_draft.ui.data.UnsplashPhoto

@Dao
interface UnsplashDAO {

//    @Query("SELECT * FROM UnsplashItem")
//    fun getAllImages(): LiveData<List<UnsplashItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImage(image: UnsplashPhoto)
}