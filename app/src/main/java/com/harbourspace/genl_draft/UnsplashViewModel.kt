package com.harbourspace.genl_draft

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harbourspace.genl_draft.ui.data.UnsplashPhoto
import com.harbourspace.genl_draft.ui.data.cb.UnsplashResult
import com.harbourspace.myapplication.ui.api.UnsplashApiProvider
import com.harbourspace.unsplash.repository.UnsplashRepository

private const val TAG = "UnsplashViewModel"

class UnsplashViewModel(
    private val repository: UnsplashRepository
) : ViewModel(), ViewModelProvider.Factory, UnsplashResult {

    private val _item = MutableLiveData<UnsplashPhoto>()
    val item: LiveData<UnsplashPhoto> = _item

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

//    private val _collections = MutableLiveData<List<UnsplashPhoto>>()
//    val collections: LiveData<List<UnsplashPhoto>> = _collections



    fun searchImage(keyword: String) {
        provider.searchImage(keyword, this)
    }

    private val provider by lazy {
        UnsplashApiProvider()
    }


    fun saveImage(image: UnsplashPhoto) {
        if (image != null) {
            repository.insert(image)
        }
    }


    override fun onDataFetchedSuccess(image: UnsplashPhoto) {
        Log.d(TAG, "onDataFetchedSuccess | Received  image")
        _item.value = image
        _loading.value = false
    }


    override fun onDataFetchedFailed() {
        Log.e(TAG, "onDataFetchedFailed | Unable to retrieve image")
        _loading.value = false
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnsplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnsplashViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}