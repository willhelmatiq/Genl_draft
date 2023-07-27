package com.harbourspace.myapplication.ui.api

import com.harbourspace.genl_draft.ui.data.UnsplashPhoto
import com.harbourspace.genl_draft.ui.data.cb.UnsplashResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.unsplash.com/"

class UnsplashApiProvider {

    private val retrofit by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create<UnsplashApi>()
    }

    fun searchImage(keyword: String, cb: UnsplashResult) {
        retrofit.searchPhoto(keyword, 1).enqueue(object : Callback<UnsplashPhoto> {
            override fun onResponse(call: Call<UnsplashPhoto>, response: Response<UnsplashPhoto>) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onDataFetchedSuccess(response.body()!!)
                } else {
                    cb.onDataFetchedFailed()
                }
            }

            override fun onFailure(call: Call<UnsplashPhoto>, t: Throwable) {
                cb.onDataFetchedFailed()
            }
        })
    }
}
